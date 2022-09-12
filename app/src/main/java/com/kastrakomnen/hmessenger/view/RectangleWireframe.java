package com.kastrakomnen.hmessenger.view;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class RectangleWireframe {

    private FloatBuffer vertexBuffer;
    private ByteBuffer  indexBuffer;
    private FloatBuffer textureBuffer;

    private final String vertexShaderCode =
            "#version 200 es" +
                    "layout(location = 0) in vec4 a_Position;" +
                    "uniform mat4 uMVPMatrix;" +
                    "void main()" +
                    "{" +
                    "    gl_Position = uMVPMatrix * a_Position;" +
                    "}"
            ;

    // Use to access and set the view transformation
    private int vPMatrixHandle;

    private final String fragmentShaderCode =
            "#version 200 es" +
                    "precision mediump float;" +
                    "layout(location = 0) out vec4 gl_FragColor;" +
                    "uniform vec4 vColor;" +
                    "void main()" +
                    "{" +
                    "    gl_FragColor = vColor;" +
                    "}"
            ;

    private final int mProgram;

    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;

    static float[] vertices = {
            -1.0f,  1.0f, 0.0f, /* top-left     */
            1.0f,  1.0f, 0.0f, /* bottom-left  */
            1.0f, -1.0f, 0.0f, /* top-right    */
            -1.0f, -1.0f, 0.0f, /* bottom-right */
    };

    static final byte[] indices = {0, 1, 2, 0, 2, 3};

    private int positionHandle;

    private final int           vertexCount = vertices.length / COORDS_PER_VERTEX;
    private final int           vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    private int                 colorHandle;
    private float[]             color = { 1.0f, 0.6f, 0.2f, 1.0f };

    public RectangleWireframe() {
        /* **
         * Create a byte buffer object for vertices (Number of vertex * Float Size in Byte)
         * Use the device hardware's native byte order
         * Create a floating point buffer from the ByteBuffer
         * Add the coordinates to the FloatBuffer
         * Set the buffer to read the first coordinate
         */
        ByteBuffer vertexBufferObject = ByteBuffer.allocateDirect(vertices.length * Float.BYTES);
        vertexBufferObject.order(ByteOrder.nativeOrder());
        this.vertexBuffer = vertexBufferObject.asFloatBuffer();
        this.vertexBuffer.put(vertices);
        this.vertexBuffer.position(0);

        this.indexBuffer = ByteBuffer.allocateDirect(indices.length);
        this.indexBuffer.put(indices);
        this.indexBuffer.position(0);

        /* *****
         * CREATE SHADER PROGRAMS
         */

        // create empty OpenGL ES Program
        mProgram = GLES20.glCreateProgram();

        int vertexShader = AnimationLayerRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = AnimationLayerRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        GLES20.glBindAttribLocation(mProgram, 0, "a_Position");
        GLES20.glBindAttribLocation(mProgram, 1, "a_TexCoordinate");
        /* *****
         * END CREATE SHADER PROGRAMS
         */

        // add the vertex shader to program
        GLES20.glAttachShader(mProgram, vertexShader);

        // add the fragment shader to program
        GLES20.glAttachShader(mProgram, fragmentShader);

        // creates OpenGL ES program executables
        GLES20.glLinkProgram(mProgram);
    }

    public void draw(float[] mvpMatrix) {
        GLES20.glUseProgram(mProgram);

        positionHandle = GLES20.glGetAttribLocation(mProgram, "a_Position");
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);

        colorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        GLES20.glUniform4fv(colorHandle, 1, color, 0);

        vPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        GLES20.glUniformMatrix4fv(vPMatrixHandle, 1, false, mvpMatrix, 0);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, indices.length, GLES20.GL_UNSIGNED_BYTE, indexBuffer);
//        GLES20.glDisableVertexAttribArray(positionHandle);
    }
}
