package com.kastrakomnen.hmessenger.view;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.SystemClock;

import java.util.Random;

public class AnimationLayerRenderer implements GLSurfaceView.Renderer {

    private Triangle mTriangle;

    private Triangle mTriangleBundle[];

    private final float[] vPMatrix          = new float[16];
    private final float[] projectionMatrix  = new float[16];
    private final float[] viewMatrix        = new float[16];
    private final float[] rotationMatrix    = new float[16];
    private final float[] translationMatrix = new float[16];
    private final float[] tmp = new float[16];

    private float r = 0.4f;
    private float r_increment = 0.00f;
    private final int NUMBER_OF_TRIANGLE = 500;

    private Random random = new Random();

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        mTriangle = new Triangle();
        mTriangleBundle = new Triangle[NUMBER_OF_TRIANGLE];

        for (int i=0; i<NUMBER_OF_TRIANGLE; i++) {
            mTriangleBundle[i] = new Triangle();
        }

    }

    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        r += r_increment;
        r = r%0.9f;
        float map_ratio = 1.0f/r;

        float[] scratch = new float[16];

        // Set the camera position (View matrix)
        Matrix.setLookAtM(viewMatrix, 0, 0, 0, 3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

        for (int i=0; i<mTriangleBundle.length/4; i++) {
            float x = (float) Math.sqrt(Math.pow(r,2) - Math.pow(random.nextFloat()*map_ratio, 2));
            float y = (float) Math.sqrt(Math.pow(r,2) - Math.pow(x, 2));
            Matrix.setIdentityM(translationMatrix, 0);
            Matrix.translateM(translationMatrix, 0, x, y, 0);
            Matrix.multiplyMM(scratch, 0, vPMatrix, 0, translationMatrix, 0);
            mTriangleBundle[i].draw(scratch);
        }

        for (int i=mTriangleBundle.length/4; i<2*mTriangleBundle.length/4; i++) {
            float x = (float) Math.sqrt(Math.pow(r,2) - Math.pow(random.nextFloat()*map_ratio, 2));
            float y = (float) Math.sqrt(Math.pow(r,2) - Math.pow(x, 2));
            Matrix.setIdentityM(translationMatrix, 0);
            Matrix.translateM(translationMatrix, 0, x, -1*y, 0);
            Matrix.multiplyMM(scratch, 0, vPMatrix, 0, translationMatrix, 0);
            mTriangleBundle[i].draw(scratch);
        }

        for (int i=2*mTriangleBundle.length/4; i<3*mTriangleBundle.length/4; i++) {
            float x = (float) Math.sqrt(Math.pow(r,2) - Math.pow(random.nextFloat()*map_ratio, 2));
            float y = (float) Math.sqrt(Math.pow(r,2) - Math.pow(x, 2));
            Matrix.setIdentityM(translationMatrix, 0);
            Matrix.translateM(translationMatrix, 0, -1*x, -1*y, 0);
            Matrix.multiplyMM(scratch, 0, vPMatrix, 0, translationMatrix, 0);
            mTriangleBundle[i].draw(scratch);
        }

        for (int i=3*mTriangleBundle.length/4; i<mTriangleBundle.length; i++) {
            float x = (float) Math.sqrt(Math.pow(r,2) - Math.pow(random.nextFloat()*map_ratio, 2));
            float y = (float) Math.sqrt(Math.pow(r,2) - Math.pow(x, 2));
            Matrix.setIdentityM(translationMatrix, 0);
            Matrix.translateM(translationMatrix, 0, -1*x, y, 0);
            Matrix.multiplyMM(scratch, 0, vPMatrix, 0, translationMatrix, 0);
            mTriangleBundle[i].draw(scratch);
        }

    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width / height;

        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}
