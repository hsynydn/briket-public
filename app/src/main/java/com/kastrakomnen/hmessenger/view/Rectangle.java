package com.kastrakomnen.hmessenger.view;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Rectangle {

    private FloatBuffer vertexBuffer;
    private ByteBuffer  indexBuffer;
    private FloatBuffer textureBuffer;

    private final String vertexShaderCode =
            "#version 200 es" +
            "layout(location = 0) in vec4 a_Position;" +
//            "layout(location = 1) in vec2 a_TexCoordinate;" +
            "uniform mat4 uMVPMatrix;" +
//            "out vec2 v_TexCoordinate;" +
            "void main()" +
            "{" +
            "    gl_Position = uMVPMatrix * a_Position;" +
//            "    v_TexCoordinate = a_TexCoordinate;" +
            "}"
            ;

    // Use to access and set the view transformation
    private int vPMatrixHandle;

    private final String fragmentShaderCode =
            "#version 200 es" +
            "precision mediump float;" +
//            "in vec2 v_TexCoordinate;" +
            "layout(location = 0) out vec4 gl_FragColor;" +
            "uniform sampler2D u_Texture;" +
            "void main()" +
            "{" +
            "    gl_FragColor = 0;" +
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

    static float[] textureCoordinateData  = {
            0.0f, 0.0f,
            1.0f, 0.0f,
            1.0f, 1.0f,
            0.0f, 1.0f
    };

    static final byte[] indices = {0, 1, 2, 0, 2, 3};

    private Bitmap bitmap;

    private int positionHandle;
    private int textureCoordinateHandle;

    private final int           vertexCount = vertices.length / COORDS_PER_VERTEX;
    private final int           vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex
    private final FloatBuffer   mTextureCoordinates;
    private int                 mTextureUniformHandle;
    private int                 mTextureCoordinateHandle;
    private final int           mTextureCoordinateDataSize = 2;
    private int                 mTextureDataHandle;

    public Rectangle(Bitmap bitmap) {

        this.bitmap = bitmap;

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

        mTextureCoordinates = ByteBuffer.allocateDirect(textureCoordinateData.length * Float.BYTES)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        mTextureCoordinates.put(textureCoordinateData).position(0);

        /* *****
         * CREATE SHADER PROGRAMS
         */

        // create empty OpenGL ES Program
        mProgram = GLES20.glCreateProgram();

        int vertexShader = AnimationLayerRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = AnimationLayerRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        GLES20.glBindAttribLocation(mProgram, 0, "a_Position");
//        GLES20.glBindAttribLocation(mProgram, 1, "a_TexCoordinate");

        // add the vertex shader to program
        GLES20.glAttachShader(mProgram, vertexShader);

        // add the fragment shader to program
        GLES20.glAttachShader(mProgram, fragmentShader);

        // creates OpenGL ES program executables
        GLES20.glLinkProgram(mProgram);

        /* *****
         * END CREATE SHADER PROGRAMS
         */


        /* *****
         * TEXTURES
         */

        // Generate one texture pointer...
//        int[] textureHandles = new int[1];
//        GLES20.glGenTextures(1, textureHandles, 0);
//        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandles[0]);
//        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
//        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
//        GLUtils.texImage2D(
//                GLES20.GL_TEXTURE_2D, // texture target
//                0, // mipmap level
//                bitmap,
//                0 // border, ignored
//        );
//        mTextureDataHandle = GLES20.glGetUniformLocation(mProgram, "u_Texture");
//        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
//        GLES20.glUniform1i(mTextureDataHandle, 0);
//        bitmap.recycle();

        /* *****
         * END TEXTURES
         */

    }

    public void draw(float[] mvpMatrix) {
        GLES20.glUseProgram(mProgram);

        positionHandle = GLES20.glGetAttribLocation(mProgram, "a_Position");
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);

//        mTextureCoordinateHandle = GLES20.glGetAttribLocation(mProgram, "a_TexCoordinate");
//        mTextureCoordinates.position(0);
//        GLES20.glVertexAttribPointer(
//                mTextureCoordinateHandle,
//                mTextureCoordinateDataSize,
//                GLES20.GL_FLOAT,
//                false,
//                0,
//                mTextureCoordinates
//        );
//        GLES20.glEnableVertexAttribArray(mTextureCoordinateHandle);

        vPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        GLES20.glUniformMatrix4fv(vPMatrixHandle, 1, false, mvpMatrix, 0);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, indices.length, GLES20.GL_UNSIGNED_BYTE, indexBuffer);

        GLES20.glDisableVertexAttribArray(positionHandle);
    }
}
