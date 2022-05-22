package com.kastrakomnen.hmessenger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class PlayBoardHead extends View {

    public static final String TAG ="PlayBoard";

    private Movie movie;
    private long moviestart;

    public PlayBoardHead(Context context) {
        super(context);
    }

    public PlayBoardHead(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayBoardHead(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PlayBoardHead(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.i(TAG, Integer.toString(widthMeasureSpec));
        Log.i(TAG, Integer.toString(heightMeasureSpec));

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        Log.i(TAG, "widthSize :: " + widthSize);
        Log.i(TAG, "heightSize :: " + heightSize);
    }
}

