package com.kastrakomnen.hmessenger.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.kastrakomnen.hmessenger.R;

public class Card extends View {

    private Drawable cardImage;
    private Drawable star;
    private Rect border;

    public Card(Context context) {
        super(context);
    }

    public Card(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Card(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Card(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        cardImage.draw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(256, 256);

        cardImage = getContext().getDrawable(R.drawable.brick_style_shady_red);
        border = new Rect(0, 0, 256, 256);
    }
}
