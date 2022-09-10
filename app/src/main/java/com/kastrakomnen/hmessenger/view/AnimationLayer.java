package com.kastrakomnen.hmessenger.view;

import android.content.Context;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class AnimationLayer extends GLSurfaceView {

    private final AnimationLayerRenderer animationLayerRenderer;

    public AnimationLayer(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

//        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
//        getHolder().setFormat(PixelFormat.RGBA_8888);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        setZOrderOnTop(true);

        animationLayerRenderer = new AnimationLayerRenderer(context);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(animationLayerRenderer);

        // Render the view only when there is a change in the drawing data
        // Requires requestRender call explicitly
//        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public AnimationLayer(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

//        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
//        getHolder().setFormat(PixelFormat.RGBA_8888);

        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        setZOrderOnTop(true);

        animationLayerRenderer = new AnimationLayerRenderer(context);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(animationLayerRenderer);

        // Render the view only when there is a change in the drawing data
        // Requires requestRender call explicitly
//        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
