package com.kastrakomnen.hmessenger.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.kastrakomnen.hmessenger.R;

import java.util.ArrayList;

public class Recycler extends View {

    private static final String TAG ="Recycler";

    private int width = 0;
    private int height = 0;

    private int top = 0;
    private int left = 0;
    private int bottom = 0;
    private int right = 0;

    private float x_raw_1 = 0f;
    private float x_raw_2 = 0f;
    private float x_acc;

    private float frame_height = 512;
    private float frame_width = 512;

    private ArrayList<Drawable> elements = new ArrayList<>();
    private ArrayList<Rect> visible_region = new ArrayList<>();

    private Drawable star;

    private Rect middleRect_prev;
    private Rect middleRect;
    private Rect middleRect_next;

//    private ArrayList<long> touchMoments

    private int visible_element_index = 0;

    public Recycler(Context context) {
        super(context);
    }

    public Recycler(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Recycler(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Recycler(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        Paint paint = new Paint();
//        paint.setColor(Color.WHITE);
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawPaint(paint);
//
//        paint.setColor(Color.BLACK);
//        paint.setTextSize(56);
//        canvas.drawText("Some Text", width/2-112, height/2-56, paint);

        for (int i=0; i<3; i++) {
            elements.get(i).setBounds(visible_region.get(i));
            elements.get(i).draw(canvas);
            star.setBounds(
                    new Rect(
                            (int) (visible_region.get(i).left + frame_width/2) - 64,
                            visible_region.get(i).top - 64,
                            (int) (visible_region.get(i).left + frame_width/2) + 64,
                            visible_region.get(i).top + 64
                    )
                );
            star.draw(canvas);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, 640);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Log.i(TAG, "onSizeChanged");

        width = w;
        height = h;

        left = (int) (width/2 - frame_width/2);
        right = (int) (width/2 + frame_width/2);
        top = (int) (height/2 - frame_height/2);
        bottom = (int) (height/2 + frame_height/2);

        elements.add(getContext().getDrawable(R.drawable.brick_style_shady_red));
        elements.add(getContext().getDrawable(R.drawable.brick_style_shady_blue));
        elements.add(getContext().getDrawable(R.drawable.brick_style_shady_orange));
        elements.add(getContext().getDrawable(R.drawable.brick_style_shady_red));
        elements.add(getContext().getDrawable(R.drawable.brick_style_shady_blue));
        star = getContext().getDrawable(R.drawable.star);

        middleRect_prev = new Rect((int)(left - frame_width - 50), top, (int)(right - frame_width - 50), bottom);
        middleRect = new Rect(left, top, right, bottom);
        middleRect_next = new Rect((int)(left + frame_width + 50), top, (int)(right + frame_width + 50), bottom);

        visible_region.add(middleRect_prev);
        visible_region.add(middleRect);
        visible_region.add(middleRect_next);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "event.getRawX() ─ " + event.getRawX());
        Log.i(TAG, "event.getRawY() ─ " + event.getRawY());

        if (Math.abs(x_raw_1 - event.getRawX()) > 100){
            x_raw_1 = 0;
        }

        if (x_raw_1 == 0){
            x_raw_1 = event.getRawX();
            x_raw_2 = x_raw_1;
        }else{
            x_raw_1 = x_raw_2;
            x_raw_2 = event.getRawX();
        }

        middleRect_prev.left += 2 * (int) (x_raw_2 - x_raw_1);
        middleRect_prev.right = (int) (middleRect_prev.left + frame_width);

        middleRect.left += 2 * (int) (x_raw_2 - x_raw_1);
        middleRect.right = (int) (middleRect.left + frame_width);

        middleRect_next.left += 2 * (int) (x_raw_2 - x_raw_1);
        middleRect_next.right = (int) (middleRect_next.left + frame_width);

        invalidate();
        return true;
    }
}
