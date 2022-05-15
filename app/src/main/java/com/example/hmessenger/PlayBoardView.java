package com.example.hmessenger;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class PlayBoardView extends View {

    public static final String TAG ="PlayBoard";

    public ArrayList<Rect>      board;
    public ArrayList<Paint>     paints;
    public ArrayList<Drawable>  drawables;

    public ArrayList<Rect>      board2;
    public ArrayList<Paint>     paints2;
    public ArrayList<Drawable>  drawables2;

    public ArrayList<ArrayList<Path>>       animations;
    public ArrayList<ArrayList<Boolean>>    animations_flags;
    public ArrayList<Paint>                 laser_beam_paints;

    public Drawable orange;
    public Drawable red;
    public Drawable blue;
    public Drawable green;
    public Drawable empty;
    public Drawable blank;

    private Random random;

    public PlayBoardView(Context context) {
        super(context);
        board = new ArrayList<>();
        animations = new ArrayList<ArrayList<Path>>();
        animations_flags = new ArrayList<ArrayList<Boolean>>();
        laser_beam_paints = new ArrayList<Paint>();
        random = new Random();
    }

    public PlayBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        board = new ArrayList<>();
        animations = new ArrayList<ArrayList<Path>>();
        animations_flags = new ArrayList<ArrayList<Boolean>>();
        laser_beam_paints = new ArrayList<Paint>();
        random = new Random();
    }

    public PlayBoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        board = new ArrayList<>();
        animations = new ArrayList<ArrayList<Path>>();
        animations_flags = new ArrayList<ArrayList<Boolean>>();
        laser_beam_paints = new ArrayList<Paint>();
        random = new Random();
    }

    public PlayBoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        board = new ArrayList<>();
        animations = new ArrayList<ArrayList<Path>>();
        animations_flags = new ArrayList<ArrayList<Boolean>>();
        laser_beam_paints = new ArrayList<Paint>();
        random = new Random();
    }

    void myInflate(int width, int height, int sqrtLen){

        Random random = new Random();

        int left_base = (width - 12 * sqrtLen) / 2;
        int top_base = (height - 24 * sqrtLen) / 2;

        int left = left_base;
        int right = left + sqrtLen;
        int top = top_base;
        int bottom = top + sqrtLen;

        /* Number of rows */
        for (int j = 0; j<24; j++){

            /* Create 16 Laser Beam Path for every row
             * Connection Point is the middle of left side */
            ArrayList<Path> tmp = new ArrayList<Path>();
            for (int i = 0; i<16; i++){
                Path p = new Path();
                p.moveTo(left,sqrtLen/2 + top);
                tmp.add(p);
            }

            /* Create show flags of laser beams */
            ArrayList<Boolean> tmp_bool = new ArrayList<Boolean>();
            for (int i = 0; i<16; i++){
                tmp_bool.add(false);
            }

            for (int i = 0; i<12; i++){

                /* Make some additions to laser beam paths
                 * B careful, last element will connect to righ side */
                if (i==11){
                    for (Path p : tmp){
                        p.lineTo( right, top + sqrtLen/2);
                    }
                }else{
                    for (Path p : tmp){
                        p.lineTo(random.nextInt(sqrtLen/2) + left + sqrtLen/2, random.nextInt(sqrtLen/2) + top + sqrtLen/2);
                    }
                }

                board.add(new Rect(left, top, right, bottom));
                left = right;
                right += sqrtLen;
            }

            animations.add(tmp);
            animations_flags.add(tmp_bool);

            top     = bottom;
            bottom += sqrtLen;

            left    = left_base;
            right   = left + sqrtLen;
        }

        paints = new ArrayList<>();
        drawables = new ArrayList<>();

        orange  = getContext().getDrawable(R.drawable.brick_style_shady_orange);
        red     = getContext().getDrawable(R.drawable.brick_style_shady_red);
        blue    = getContext().getDrawable(R.drawable.brick_style_shady_blue);
        green   = getContext().getDrawable(R.drawable.brick_style_shady_green);
        empty   = getContext().getDrawable(R.drawable.shape);
        blank   = getContext().getDrawable(R.drawable.blank);

        for (Rect rect : board) {
            Paint p = new Paint();
            p.setAntiAlias(true);
            p.setStyle(Paint.Style.STROKE);
            p.setColor(0xffffcccc);
            p.setStrokeWidth(4);
            paints.add(p);
            drawables.add(empty);
        }

        Paint paint1 = new Paint();
        paint1.setAntiAlias(true);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setColor(0xffb3b3ff);
        paint1.setStrokeWidth(4);
        laser_beam_paints.add(paint1);

        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(0xffff00bf);
        paint2.setStrokeWidth(4);
        laser_beam_paints.add(paint2);

        Paint paint3 = new Paint();
        paint3.setAntiAlias(true);
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setColor(0xff00e64d);
        paint3.setStrokeWidth(4);
        laser_beam_paints.add(paint3);

        Paint paint4 = new Paint();
        paint4.setAntiAlias(true);
        paint4.setStyle(Paint.Style.STROKE);
        paint4.setColor(0xffccfff5);
        paint4.setStrokeWidth(4);
        laser_beam_paints.add(paint4);

    }

    void myInflates2(int sqrtLen){

        board2 = new ArrayList<>();

        int left_base   = board.get(11).right + 10;
        int top_base    = board.get(11).top;

        int left = left_base;
        int right = left + sqrtLen;
        int top = top_base;
        int bottom = top + sqrtLen;

        for (int j = 0; j<4; j++){
            for (int i = 0; i<4; i++){
                board2.add(new Rect(left, top, right, bottom));
                left = right;
                right += sqrtLen;
            }

            top     = bottom;
            bottom += sqrtLen;

            left    = left_base;
            right   = left + sqrtLen;
        }

        paints2 = new ArrayList<>();
        drawables2 = new ArrayList<>();

        for (Rect rect : board2) {
            Paint p = new Paint();
            p.setAntiAlias(true);
            p.setStyle(Paint.Style.STROKE);
            p.setColor(0xffffcccc);
            p.setStrokeWidth(4);
            paints2.add(p);
            drawables2.add(blank);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.i(TAG, Integer.toString(this.getLayoutParams().width));
        Log.i(TAG, Integer.toString(this.getLayoutParams().height));

        int j = 0;
        for (Drawable drawable : drawables) {
            drawable.setBounds(board.get(j));
            drawable.draw(canvas);
            j++;
        }

        int k = 0;
        for (Drawable drawable : drawables2) {
            drawable.setBounds(board2.get(k));
            drawable.draw(canvas);
            k++;
        }

        int f_i = 0;
        for (ArrayList<Boolean> flags : animations_flags){
            int f_j = 0;
            for (Boolean flag : flags){
                if (flag) canvas.drawPath(animations.get(f_i).get(f_j), laser_beam_paints.get(random.nextInt(4)));
                f_j++;
            }
            f_i++;
        }

        invalidate();
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
        super.onSizeChanged(xNew, yNew, xOld, yOld);

        myInflate(xNew, yNew, 60);
        myInflates2(30);
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
