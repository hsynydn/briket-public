package com.kastrakomnen.hmessenger.hdroid;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;

import androidx.annotation.Nullable;

import com.kastrakomnen.hmessenger.R;
import com.kastrakomnen.hmessenger.model.BasePublisher;
import com.kastrakomnen.hmessenger.model.set.Brick;
import com.kastrakomnen.hmessenger.model.set.BrickType;
import com.kastrakomnen.hmessenger.model.display.DisplayData;

import java.util.ArrayList;
import java.util.Random;

public class PlayBoardView extends View {

    private static final String TAG = "{hdroid.PlayBoardView}";

    private BasePublisher basePublisher;

    private int boardHeight;
    private int boardWidth;

    private final int animDuration = 200;

    private ArrayList<ArrayList<Rect>> permanentRectBounds;
    private ArrayList<ArrayList<Rect>> brickRectBounds;
    private ArrayList<ArrayList<Drawable>> brickDrawables;
    private ArrayList<Rect> removeObjects;

    private Random random;
    private int randomInteger;

    private ArrayList<Boolean>  scorePopUPGates;
    private ArrayList<String>   scorePopUPTexts;
    private ValueAnimator       scorePopUPAnimator;
    private Paint               scorePopUPPaint;

    private int screenHeight;
    private int screenWidth;
    private int squareDimension;
    private Paint empty;

    private Paint testPaint;

    private Drawable brick;
    private Drawable brick_red;
    private Drawable brick_green;
    private Drawable brick_blue;
    private Drawable brick_pink;
    private Drawable brick_star;
    private Drawable brick_orange;
    private Drawable coin;
    private Drawable emptyRegion;
    private Drawable lineup;

    public PlayBoardView(Context context) {
        super(context);
        onInit(context);
    }

    public PlayBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        onInit(context);
    }

    public PlayBoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onInit(context);
    }

    public PlayBoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        onInit(context);
    }

    private void onInit(Context context){

        basePublisher = new BasePublisher();

        random = new Random();
        randomInteger = random.nextInt(1000);

        permanentRectBounds = new ArrayList<>();
        brickRectBounds = new ArrayList<>();
        brickDrawables = new ArrayList<>();
        removeObjects = new ArrayList<>();

        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        screenHeight = context.getResources().getDisplayMetrics().heightPixels;

        testPaint = new Paint();
        testPaint.setAntiAlias(true);
        testPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        testPaint.setColor(0xff00ff00);
        testPaint.setStrokeWidth(2);

        brick           = getContext().getDrawable(R.drawable.ic_fuscia_briket);
        brick_red       = getContext().getDrawable(R.drawable.ic_fuscia_briket);
        brick_green     = getContext().getDrawable(R.drawable.ic_green_briket);
        brick_blue      = getContext().getDrawable(R.drawable.ic_blue_briket);
        brick_pink      = getContext().getDrawable(R.drawable.ic_raspberry_briket);
        brick_orange    = getContext().getDrawable(R.drawable.ic_orange_briket);
        coin            = getContext().getDrawable(R.drawable.coin);
        brick_star      = getContext().getDrawable(R.drawable.ic_tough_briket);
        emptyRegion     = getContext().getDrawable(R.drawable.shape);
        lineup          = getContext().getDrawable(R.drawable.ic_lineup);

        scorePopUPGates = new ArrayList<>();
        scorePopUPTexts = new ArrayList<>();

        scorePopUPAnimator = ValueAnimator.ofInt(0, 75);
        scorePopUPAnimator.setDuration(700);
        scorePopUPAnimator.setInterpolator(new BounceInterpolator());
        scorePopUPAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                scorePopUPPaint.setTextSize((Integer) valueAnimator.getAnimatedValue());
                invalidate();
            }
        });

        scorePopUPAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                for (int i = 0; i < scorePopUPGates.size(); i++) {
                    scorePopUPGates.set(i, false);
                }
                invalidate();
                randomInteger = random.nextInt(1000);
            }
        });

        scorePopUPPaint = new Paint();
        scorePopUPPaint.setAntiAlias(true);
        scorePopUPPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        scorePopUPPaint.setColor(0xff993399);
        scorePopUPPaint.setStrokeWidth(4);
        scorePopUPPaint.setTextSize(0);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (ArrayList<Rect> row : permanentRectBounds) {
            for (Rect rect: row) {
                emptyRegion.setBounds(rect);
                emptyRegion.draw(canvas);
            }
        }

        int rowIndex = 0;
        for (ArrayList<Drawable> row : brickDrawables) {
            int colIndex = 0;
            for (Drawable drawable: row) {
                drawable.setBounds(permanentRectBounds.get(rowIndex).get(colIndex));
                drawable.draw(canvas);
                colIndex++;
            }
            rowIndex++;
        }

        for (int i = 0; i < scorePopUPGates.size(); i++) {
            if(scorePopUPGates.get(i)){

                lineup.setBounds(
                        new Rect(
                                permanentRectBounds.get(i).get(0).left - 50,
                                permanentRectBounds.get(i).get(0).top,
                                permanentRectBounds.get(i).get(boardWidth - 1).right + 50,
                                permanentRectBounds.get(i).get(boardWidth - 1).bottom
                                )
                );
                lineup.draw(canvas);

                if (!scorePopUPAnimator.isRunning()){
                    scorePopUPAnimator.start();
                }

                canvas.drawText(
                        "+" + scorePopUPTexts.get(i),
                        permanentRectBounds.get(i).get((i*randomInteger)%boardWidth).left,
                        permanentRectBounds.get(i).get((i*randomInteger)%boardWidth).top,
                        scorePopUPPaint);
            }
        }
    }

    public void create(int height, int width) {

        boardHeight = height;
        boardWidth = width;

        squareDimension = (int) (screenWidth / ((1.5) * width));

        Log.i(TAG, "screenWidth :: " + screenWidth);
        Log.i(TAG, "screenHeight :: " + screenHeight);
        Log.i(TAG, "squareDimension :: " + squareDimension);

        for (int i = 0; i < height; i++) {
            permanentRectBounds.add(new ArrayList<>());
        }

        for (int i = 0; i < height; i++) {
            brickRectBounds.add(new ArrayList<>());
        }

        for (int i = 0; i < height; i++) {
            brickDrawables.add(new ArrayList<>());
        }

        int left_base   = (screenWidth - width * squareDimension) / 2;
        int top_base    = (screenHeight - height * squareDimension) / 2;

        int left    = left_base;
        int right   = left + squareDimension;
        int top     = top_base;
        int bottom  = top + squareDimension;

        for (ArrayList<Rect> row : permanentRectBounds) {
            for (int i = 0; i < width; i++) {
                row.add(new Rect(left, top, right, bottom));
                left = right;
                right += squareDimension;
            }
            top     = bottom;
            bottom += squareDimension;

            left    = left_base;
            right   = left + squareDimension;
        }

        for (ArrayList<Rect> row : brickRectBounds) {
            for (int i = 0; i < width; i++) {
                row.add(null);
            }
        }

        for (ArrayList<Drawable> row : brickDrawables) {
            for (int i = 0; i < width; i++) {
                row.add(emptyRegion);
            }
        }

        empty = new Paint();
        empty.setAntiAlias(true);
        empty.setStyle(Paint.Style.STROKE);
        empty.setColor(0x77fcba03);
        empty.setStrokeWidth(2);

        for (int i = 0; i < height; i++) {
            scorePopUPGates.add(false);
            scorePopUPTexts.add(null);
        }
    }
//
//    @Override
//    public void create(DisplayData.Brick brick, Position at) {
//
//        Log.i(TAG, " called create(DisplayData.Brick brick, Position at)");
//
//        brickRectBounds.get(at.getY()).set(at.getX(), new Rect(permanentRectBounds.get(at.getY()).get(at.getX())));
//
//        brickRectBounds.get(at.getY()).get(at.getX()).top = (brickRectBounds.get(at.getY()).get(at.getX()).top + squareDimension/2);
//        brickRectBounds.get(at.getY()).get(at.getX()).bottom =  (brickRectBounds.get(at.getY()).get(at.getX()).bottom - squareDimension/2);
//        brickRectBounds.get(at.getY()).get(at.getX()).left =  (brickRectBounds.get(at.getY()).get(at.getX()).left + squareDimension/2);
//        brickRectBounds.get(at.getY()).get(at.getX()).right =  (brickRectBounds.get(at.getY()).get(at.getX()).right - squareDimension/2);
//
//        float tmpTop = brickRectBounds.get(at.getY()).get(at.getX()).top;
//        float tmpBottom = brickRectBounds.get(at.getY()).get(at.getX()).bottom;
//        float tmpLeft = brickRectBounds.get(at.getY()).get(at.getX()).left;
//        float tmpRight = brickRectBounds.get(at.getY()).get(at.getX()).right;
//
//        ValueAnimator animator = ValueAnimator.ofInt(0, squareDimension/2);
//        animator.setDuration(animDuration);
//        animator.setInterpolator(new BounceInterpolator());
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                if (brickRectBounds.get(at.getY()).get(at.getX())!=null){
//                    brickRectBounds.get(at.getY()).get(at.getX()).top = (int) (tmpTop - (Integer)valueAnimator.getAnimatedValue());
//                    brickRectBounds.get(at.getY()).get(at.getX()).bottom = (int) (tmpBottom + (Integer)valueAnimator.getAnimatedValue());
//                    brickRectBounds.get(at.getY()).get(at.getX()).left = (int) (tmpLeft - (Integer)valueAnimator.getAnimatedValue());
//                    brickRectBounds.get(at.getY()).get(at.getX()).right = (int) (tmpRight + (Integer)valueAnimator.getAnimatedValue());
//                }
//                invalidate();
//            }
//        });
//
//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                publish();
//            }
//        });
//
//        new Handler(Looper.getMainLooper()).post(new Runnable() {
//            @Override
//            public void run() {
//                animator.start();
//            }
//        });
//    }

//    @Override
//    public void move(DisplayData.Brick brick, Position from, Position to) {
//
//    }

    public void refresh(ArrayList<ArrayList<Brick>> board) {

        for (ArrayList<Drawable> row : brickDrawables) {
            for (int i = 0; i < row.size(); i++) {
                row.set(i, emptyRegion);
            }
        }

        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {

                Brick brick = board.get(i).get(j);

                if (brick == null) continue;

                if (brick.getBrickType() == BrickType.NORMAL){
                    switch (brick.getSet().getSetType()){
                        case BOX:
                            brickDrawables.get(i).set(j, brick_blue);
                            break;
                        case LINE:
                            brickDrawables.get(i).set(j, brick_orange);
                            break;
                        case T:
                            brickDrawables.get(i).set(j, brick_green);
                            break;
                        case J:
                        case Z:
                            brickDrawables.get(i).set(j, brick_red);
                            break;
                        case L:
                            brickDrawables.get(i).set(j, brick_pink);
                            break;
                    }
                }else if (brick.getBrickType() == BrickType.COIN){
                    brickDrawables.get(i).set(j, brick_pink);
                }else if (brick.getBrickType() == BrickType.STAR){
                    brickDrawables.get(i).set(j, brick_star);
                }
            }
        }
        for (ArrayList<Brick> row : board) {
            for (Brick brick : row) {

            }
        }

        invalidate();
//        publish();
    }

//    @Override
//    public void move(ArrayList<DisplayData.Brick> brickArrayList, ArrayList<Position> fromPositions, ArrayList<Position> toPositions) {
//
//        if (fromPositions.isEmpty() || toPositions.isEmpty()){
//            publish();
//            return;
//        }
//
//        if (fromPositions.size() != toPositions.size()) {
//            publish();
//            return;
//        }
//
//        /* existingBricks are holding rectangular regions of bricks
//        * when a move occurs, immediately updates index of moved brick
//        * but new locations rectangular data is old, and will be updated by animator
//        * */
//
//        /* Calculate the amount of index */
//        int deltaXIndex = toPositions.get(0).getX() - fromPositions.get(0).getX();
//        int deltaYIndex = toPositions.get(0).getY() - fromPositions.get(0).getY();
//
//        /* This list holds drawable start positions of bricks */
//        ArrayList<RectF> animationStartPositions = new ArrayList<>();
//        for (int i = 0; i < fromPositions.size(); i++) {
//
//            Position from = fromPositions.get(i);
//
//            /* Hold this data to calculate animation */
//            animationStartPositions.add(new RectF(permanentRectBounds.get(from.getY()).get(from.getX())));
//
//            /* Remove bricks from positions in visual buffer */
//            brickRectBounds.get(from.getY()).set(from.getX(), null);
//        }
//
//        for (int i = 0; i < fromPositions.size(); i++) {
//            Position from = fromPositions.get(i);
//            Position to = toPositions.get(i);
//
//            /* Add bricks into new visual buffer, ATTENTION, rectangular region is still old
//             * Wait animator update */
//            brickRectBounds.get(to.getY()).set(to.getX(), new Rect(permanentRectBounds.get(from.getY()).get(from.getX())));
//        }
//
//        // #AYIKOL This values can also be negative. Amount of shift pixels
//        int deltaXPx = squareDimension * deltaXIndex;
//        int deltaYPx = squareDimension * deltaYIndex;
//
//        ValueAnimator horizontalAnimator = ValueAnimator.ofInt(0, deltaXPx);
//        horizontalAnimator.setDuration(animDuration);
//        horizontalAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                int i = 0;
//                for (Position to : toPositions) {
//                    if (brickRectBounds.get(to.getY()).get(to.getX()) == null) continue;
//                    brickRectBounds.get(to.getY()).get(to.getX()).left = (int) (animationStartPositions.get(i).left + (Integer)valueAnimator.getAnimatedValue());
//                    brickRectBounds.get(to.getY()).get(to.getX()).right = (int) (animationStartPositions.get(i).right + (Integer)valueAnimator.getAnimatedValue());
//                    i++;
//                }
//                invalidate();
//            }
//        });
//
//        horizontalAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                publish();
//            }
//        });
//
//        ValueAnimator verticalAnimator = ValueAnimator.ofInt(0, deltaYPx);
//        verticalAnimator.setDuration(animDuration);
//        verticalAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                int i = 0;
//                for (Position to : toPositions) {
//                    if (brickRectBounds.get(to.getY()).get(to.getX()) == null) continue;
//                    brickRectBounds.get(to.getY()).get(to.getX()).top = (int) (animationStartPositions.get(i).top + (Integer)valueAnimator.getAnimatedValue());
//                    brickRectBounds.get(to.getY()).get(to.getX()).bottom = (int) (animationStartPositions.get(i).bottom + (Integer)valueAnimator.getAnimatedValue());
//                    i++;
//                }
//                invalidate();
//            }
//        });
//
//        verticalAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                publish();
//            }
//        });
//
//        new Handler(Looper.getMainLooper()).post(new Runnable() {
//            @Override
//            public void run() {
//                horizontalAnimator.start();
//                verticalAnimator.start();
//            }
//        });
//    }

//    @Override
//    public void rotate(ArrayList<DisplayData.Brick> brickArrayList, ArrayList<Position> fromPositions, ArrayList<Position> toPositions) {
//
//        if (fromPositions.isEmpty() || toPositions.isEmpty()) {
//            publish();
//            return;
//        }
//
//        if (fromPositions.size() != toPositions.size()) {
//            publish();
//            return;
//        }
//
//        /* existingBricks is holding rectangular regions of bricks
//         * when a move occurs, immediately updates index of moved brick
//         * but new locations rectangular data is old, and will be updated by animator
//         * */
//
//        /* This list holds drawable start positions of bricks */
//        ArrayList<RectF> animationStartPositions = new ArrayList<>();
//        for (int i = 0; i < fromPositions.size(); i++) {
//
//            Position from = fromPositions.get(i);
//
//            /* Hold this data to calculate animation */
//            animationStartPositions.add(new RectF(permanentRectBounds.get(from.getY()).get(from.getX())));
//
//            /* Remove bricks from positions in visual buffer */
//            brickRectBounds.get(from.getY()).set(from.getX(), null);
//        }
//
//        for (int i = 0; i < fromPositions.size(); i++) {
//            Position from = fromPositions.get(i);
//            Position to = toPositions.get(i);
//
//            /* Add bricks into new visual buffer, ATTENTION, rectangular region is still old
//             * Wait animator update */
//            brickRectBounds.get(to.getY()).set(to.getX(), new Rect(permanentRectBounds.get(to.getY()).get(to.getX())));
//        }
//
////        ValueAnimator horizontalAnimator = ValueAnimator.ofInt(0, 90);
////        horizontalAnimator.setDuration(100);
////        horizontalAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
////            @Override
////            public void onAnimationUpdate(ValueAnimator valueAnimator) {
////                int i = 0;
////                for (Position to : toPositions) {
////                    if (existingBricks.get(to.getY()).get(to.getX()) == null) continue;
////                    existingBricks.get(to.getY()).get(to.getX()).left = (int) (animationStartPositions.get(i).left + (Integer)valueAnimator.getAnimatedValue());
////                    existingBricks.get(to.getY()).get(to.getX()).right = (int) (animationStartPositions.get(i).right + (Integer)valueAnimator.getAnimatedValue());
////                    i++;
////                }
////                invalidate();
////            }
////        });
//
////        new Handler(Looper.getMainLooper()).post(new Runnable() {
////            @Override
////            public void run() {
////                horizontalAnimator.start();
////            }
////        });
//
//        invalidate();
//        publish();
//    }

//    @Override
//    public void remove(DisplayData.Brick brick, Position at) {
//
////        Rect tmp = existingBricks.get(at.getY()).get(at.getX());
////
////        existingBricks.get(at.getY()).set(at.getX(), null);
////        removeObjects.add(tmp);
////
////        float tmpLeft = tmp.left;
////        float tmpRight = tmp.right;
////
////        ValueAnimator animator = ValueAnimator.ofInt(0, (int) tmpLeft);
////        animator.setDuration(animDuration);
////        animator.setInterpolator(new BounceInterpolator());
////        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
////            @Override
////            public void onAnimationUpdate(ValueAnimator valueAnimator) {
////                tmp.left = (int) (tmpLeft - (Integer)valueAnimator.getAnimatedValue());
////                tmp.right = (int) (tmpRight - (Integer)valueAnimator.getAnimatedValue());
////            }
////        });
////
////        animator.addListener(new AnimatorListenerAdapter() {
////            @Override
////            public void onAnimationEnd(Animator animation) {
////                super.onAnimationEnd(animation);
////                removeObjects.remove(tmp);
////            }
////        });
////
////        new Handler(Looper.getMainLooper()).post(new Runnable() {
////            @Override
////            public void run() {
////                animator.start();
////            }
////        });
//    }

//    @Override
//    public void removeAndRefresh(
//            ArrayList<DisplayData.Brick> brickArrayList,
//            ArrayList<Position> atPositions,
//            ArrayList<Position> board
//    ) {
//
//        ArrayList<Animator> animators = new ArrayList<>();
//
//        for (int i = 0; i < atPositions.size(); i++) {
//
//            Position at = atPositions.get(i);
//
//            /* Keep a reference of remove brick */
//            Rect removeBrick = brickRectBounds.get(at.getY()).get(at.getX());
//
//            /* Remove From Board Visual Buffer */
//            brickRectBounds.get(at.getY()).set(at.getX(), null);
//
//            /* Collect all remove brick references together */
//            removeObjects.add(removeBrick);
//
//            float tmpLeft = removeBrick.left;
//            float tmpRight = removeBrick.right;
//
//            ValueAnimator animator = ValueAnimator.ofInt(0, (int) tmpLeft);
//            animator.setDuration(3000);
//            animator.setInterpolator(new BounceInterpolator());
//            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                    removeBrick.left = (int) (tmpLeft - (Integer)valueAnimator.getAnimatedValue());
//                    removeBrick.right = (int) (tmpRight - (Integer)valueAnimator.getAnimatedValue());
//                    invalidate();
//                }
//            });
//
//            if (i == atPositions.size() - 1){
//                animator.addListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        super.onAnimationEnd(animation);
//                        removeObjects.clear();
//                        refresh(brickArrayList, board);
//                        publish();
//                    }
//                });
//            }
//
//            animators.add(animator);
//        }
//
//        new Handler(Looper.getMainLooper()).post(new Runnable() {
//            @Override
//            public void run() {
//                for (Animator animator : animators) {
//                    animator.start();
//                }
//            }
//        });
//    }
//
//    @Override
//    public void refresh(ArrayList<DisplayData.Brick> brickArrayList, ArrayList<Position> atPositions) {
//
//        for (int i = 0; i < brickRectBounds.size(); i++) {
//            for (int j = 0; j < brickRectBounds.get(i).size(); j++) {
//                brickRectBounds.get(i).set(j, null);
//            }
//        }
//
//        for (Position p : atPositions) {
//            brickRectBounds.get(p.getY()).set(p.getX(), permanentRectBounds.get(p.getY()).get(p.getX()));
//        }
//
//        invalidate();
//        publish();
//    }
//
//    @Override
//    public void startFast() {
//
//    }
//
//    @Override
//    public void startDelay(int delay) {
//
//    }
//
//    @Override
//    public void updateInfo() {
//
//    }
//
//    @Override
//    public void end() {
//
//    }

    public void popUpScore(ArrayList<DisplayData.Score> scores) {
        for (DisplayData.Score score : scores) {
            scorePopUPGates.set(score.at, true);
            scorePopUPTexts.set(score.at, Integer.toString(score.value));
        }
//        publish();
    }

//    @Override
//    public void setScore(int score) {
//    }

//    @Override
//    public void register(Subscriber subscriber) {
//        basePublisher.register(subscriber);
//    }
//
//    @Override
//    public void publish() {
//        basePublisher.publish();
//    }
}
