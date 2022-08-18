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
import android.view.View;
import android.view.animation.BounceInterpolator;

import androidx.annotation.Nullable;

import com.kastrakomnen.hmessenger.R;
import com.kastrakomnen.hmessenger.model.set.Brick;
import com.kastrakomnen.hmessenger.model.set.BrickType;
import com.kastrakomnen.hmessenger.model.display.DisplayData;

import java.util.ArrayList;
import java.util.Random;

public class PlayBoardView extends View {

    private static final String TAG = "{hdroid.PlayBoardView}";

    private int mRow;
    private int mColumn;

    private ArrayList<ArrayList<Rect>> mRectBoardObjects;
    private ArrayList<ArrayList<Drawable>> mDrawableBoardObjects;

    private Random mRandomGenerator;
    private int mRandomNumber;

    private ArrayList<Boolean>  mGateScores;
    private ArrayList<String>   mTextScores;
    private ValueAnimator       mAnimatorScore;
    private ValueAnimator       mAnimatorEvent;
    private Boolean             mGateCombo;
    private String              mTextCombo;
    private Paint               mPaintScore;

    private int     mScreenHeightPixel;
    private int     mScreenWidthPixel;
    private int     mBoardSquareDimension;
    private Paint   mPaintEmpty;

    private Paint mPaintTest;
    private Paint mPaintBoardBackground;
    private Paint mPaintTimeBoard;
    private Paint mPaintScoreBoard;
    private Paint mPaintPopup;

    private Drawable mDrawableBrickRed;
    private Drawable mDrawableBrickGreen;
    private Drawable mDrawableBrickBlue;
    private Drawable mDrawableBrickPink;
    private Drawable mDrawableBrickStar;
    private Drawable mDrawableBrickOrange;
    private Drawable mDrawableEmptyRegion;
    private Drawable mDrawableObjectiveBoard;
    private Drawable mDrawableScoreBoard;
    private Drawable mDrawableTimeBoard;

    private Rect        mRectScoreBoard;
    private int         mScoreBoardLeft;
    private int         mScoreBoardTop;
    private final int   mScoreBoardWidth    = 400;
    private final int   mScoreBoardHeight   = 150;
    private Rect        mRectScoreBoardText;
    private String      mScoreBoardText;

    private Rect        mRectObjectiveBoard;
    private int         mObjectiveBoardLeft;
    private int         mObjectiveBoardTop;
    private final int   mObjectiveBoardWidth = 150;
    private final int   mObjectiveBoardHeight = 150;
    private Rect        mRectObjectiveBoardText;
    private String      mObjectiveBoardText;
    private final ArrayList<Rect> mRectObjectiveIcons = new ArrayList<>();

    private Rect        mRectTimeBoard;
    private int         mTimeBoardLeft;
    private int         mTimeBoardTop;
    private final int   mTimeBoardWidth = 300;
    private final int   mTimeBoardHeight = 75;
    private Rect        mRectTimeBoardText;
    private String      mTimeBoardText;

    private Rect    mRectNextPattern;
    private int     mNextPatternLeft;
    private int     mNextPatternTop;
    private int     mNextPatternWidth;
    private int     mNextPatternHeight;

    private Rect    mRectBoard;
    private int     mBoardLeft;
    private int     mBoardTop;
    private int     mBoardWidth;
    private int     mBoardHeight;

    private Rect    mRectPopup;
    private int     mPopupLeft;
    private int     mPopupTop;
    private int     mPopupWidth;
    private int     mPopupHeight;
    private Rect    mRectPopupText;
    private String  mPopUpText;

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

    private void onInit(Context context){

        mScreenWidthPixel = context.getResources().getDisplayMetrics().widthPixels;
        mScreenHeightPixel = context.getResources().getDisplayMetrics().heightPixels;

        mRandomGenerator = new Random();
        mRandomNumber = mRandomGenerator.nextInt(1000);

        mRectBoardObjects       = new ArrayList<>();
        mDrawableBoardObjects   = new ArrayList<>();

        mRectTimeBoardText      = new Rect();
        mRectScoreBoardText     = new Rect();
        mRectObjectiveBoardText = new Rect();
        mRectObjectiveBoard     = new Rect();
        mRectPopupText          = new Rect();

        mGateScores             = new ArrayList<>();
        mGateCombo              = false;
        mTextScores             = new ArrayList<>();

        mScoreBoardText = "0";
        mTimeBoardText = "00:00";
        mObjectiveBoardText = "0";
        mPopUpText = "Combo";

        loadPaints();
        loadDrawables();
        loadAnimators();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw a background color for board
        canvas.drawRect(mRectBoard, mPaintBoardBackground);

        /* ****************************
         * Score Board
         */
        mDrawableScoreBoard.setBounds(mRectScoreBoard);
        mDrawableScoreBoard.draw(canvas);

        mPaintScoreBoard.getTextBounds(mScoreBoardText, 0, mScoreBoardText.length(), mRectScoreBoardText);
        canvas.drawText(
                mScoreBoardText,
                mScoreBoardLeft + mScoreBoardWidth /2 - mRectScoreBoardText.exactCenterX(),
                mScoreBoardTop + mScoreBoardHeight /2 - mRectScoreBoardText.exactCenterY(),
                mPaintScoreBoard
        );

        /* ****************************
         * Time Board
         */
        mDrawableTimeBoard.setBounds(mRectTimeBoard);
        mDrawableTimeBoard.draw(canvas);

        mPaintTest.getTextBounds(mTimeBoardText, 0, mTimeBoardText.length(), mRectTimeBoardText);
        canvas.drawText(
                mTimeBoardText,
                mTimeBoardLeft + mTimeBoardWidth /2 - mRectTimeBoardText.exactCenterX(),
                mTimeBoardTop + mTimeBoardHeight /2 - mRectTimeBoardText.exactCenterY(),
                mPaintTest
        );

        /* ****************************
         * Objective Board
         */
        mDrawableObjectiveBoard.setBounds(mRectObjectiveBoard);
        mDrawableObjectiveBoard.draw(canvas);

        mDrawableBrickOrange.setBounds(mRectObjectiveIcons.get(0));
        mDrawableBrickOrange.draw(canvas);
        mDrawableBrickBlue.setBounds(mRectObjectiveIcons.get(1));
        mDrawableBrickBlue.draw(canvas);
        mDrawableBrickRed.setBounds(mRectObjectiveIcons.get(2));
        mDrawableBrickRed.draw(canvas);

        mPaintScoreBoard.getTextBounds(mObjectiveBoardText, 0, mObjectiveBoardText.length(), mRectObjectiveBoardText);
        canvas.drawText(
                mObjectiveBoardText,
                mRectObjectiveBoard.left + mObjectiveBoardWidth /2 - mRectObjectiveBoardText.exactCenterX(),
                mRectObjectiveBoard.bottom - mObjectiveBoardHeight /2 - mRectObjectiveBoardText.exactCenterY(),
                mPaintScoreBoard
        );

        /* ****************************
         * Draw Bricks on Board
         */
        int rowIndex = 0;
        for (ArrayList<Drawable> row : mDrawableBoardObjects) {
            int colIndex = 0;
            for (Drawable drawable: row) {
                drawable.setBounds(mRectBoardObjects.get(rowIndex).get(colIndex));
                drawable.draw(canvas);
                colIndex++;
            }
            rowIndex++;
        }

        /* ****************************
         * Draw Score Pop Ups
         */
        for (int i = 0; i < mGateScores.size(); i++) {
            if(mGateScores.get(i)){
                if (!mAnimatorScore.isRunning()){
                    mAnimatorScore.start();
                }

                canvas.drawText(
                        "+" + mTextScores.get(i),
                        mRectBoardObjects.get(i).get((i* mRandomNumber)% mColumn).left,
                        mRectBoardObjects.get(i).get((i* mRandomNumber)% mColumn).top,
                        mPaintScore);
            }
        }

        /* ****************************
         * Draw Combo Pop Ups
         */
        if (mGateCombo){
            if (!mAnimatorEvent.isRunning()){
                mAnimatorEvent.start();
            }

            mPaintPopup.getTextBounds(mPopUpText, 0, mPopUpText.length(), mRectPopupText);
            canvas.drawText(
                    mPopUpText,
                    mRectPopup.left + mPopupWidth/2 - mRectPopupText.exactCenterX(),
                    mRectPopup.top + mPopupHeight/2 - mRectPopupText.exactCenterY(),
                    mPaintPopup);
        }
    }

    public void create(int height, int width) {

        mRow = height;
        mColumn = width;

        mBoardSquareDimension = (int) (mScreenWidthPixel / ((1.5) * width));

        for (int i = 0; i < height; i++) {
            mRectBoardObjects.add(new ArrayList<>());
        }

        for (int i = 0; i < height; i++) {
            mDrawableBoardObjects.add(new ArrayList<>());
        }

        int left_base   = (mScreenWidthPixel - width * mBoardSquareDimension) / 2;
        int top_base    = (mScreenHeightPixel - height * mBoardSquareDimension) / 2;

        mBoardLeft = left_base;
        mBoardTop = top_base;
        mBoardWidth = width * mBoardSquareDimension;
        mBoardHeight = height * mBoardSquareDimension;

        mRectBoard = new Rect(mBoardLeft, mBoardTop, mBoardLeft + mBoardWidth, mBoardTop + mBoardHeight);

        int left    = left_base;
        int right   = left + mBoardSquareDimension;
        int top     = top_base;
        int bottom  = top + mBoardSquareDimension;

        for (ArrayList<Rect> row : mRectBoardObjects) {
            for (int i = 0; i < width; i++) {
                row.add(new Rect(left, top, right, bottom));
                left = right;
                right += mBoardSquareDimension;
            }
            top     = bottom;
            bottom += mBoardSquareDimension;

            left    = left_base;
            right   = left + mBoardSquareDimension;
        }

        for (ArrayList<Drawable> row : mDrawableBoardObjects) {
            for (int i = 0; i < width; i++) {
                row.add(mDrawableEmptyRegion);
            }
        }

        for (int i = 0; i < height; i++) {
            mGateScores.add(false);
            mTextScores.add(null);
        }

        mTimeBoardLeft = (mScreenWidthPixel - mTimeBoardWidth) / 2;
        mTimeBoardTop = mBoardTop - mTimeBoardHeight;
        mRectTimeBoard = new Rect(mTimeBoardLeft, mTimeBoardTop, mTimeBoardLeft + mTimeBoardWidth, mTimeBoardTop + mTimeBoardHeight);

        mScoreBoardLeft = (mScreenWidthPixel - mScoreBoardWidth) / 2;
        mScoreBoardTop = (mScreenHeightPixel /2 - mBoardHeight /2) - mScoreBoardHeight - mTimeBoardHeight;
        mRectScoreBoard = new Rect(mScoreBoardLeft, mScoreBoardTop, mScoreBoardLeft + mScoreBoardWidth, mScoreBoardTop + mScoreBoardHeight);

        mPopupWidth = mRectBoard.width();
        mPopupHeight = mRectBoard.height()/4;
        mPopupLeft = mRectBoard.left;
        mPopupTop = mRectBoard.top + mBoardHeight/2 - mPopupHeight/2;
        mRectPopup = new Rect(mRectBoard.left, mPopupTop, mRectBoard.right, mPopupTop + mPopupHeight);

        int objectiveIconDimension = 40;
        mObjectiveBoardLeft = mRectScoreBoard.right + 100;
        mObjectiveBoardTop = mRectScoreBoard.top;
        mRectObjectiveBoard = new Rect(mObjectiveBoardLeft, mObjectiveBoardTop, mObjectiveBoardLeft + mObjectiveBoardWidth, mObjectiveBoardTop + mObjectiveBoardHeight);

        Rect rect1 = new Rect(
                mObjectiveBoardLeft + mObjectiveBoardWidth /2 - objectiveIconDimension/2,
                mObjectiveBoardTop - objectiveIconDimension/2,
                mObjectiveBoardLeft + mObjectiveBoardWidth /2 + objectiveIconDimension/2,
                mObjectiveBoardTop + objectiveIconDimension/2
        );

        Rect rect2 = new Rect(
                mObjectiveBoardLeft + mObjectiveBoardWidth /2 - (3 * objectiveIconDimension/2),
                mObjectiveBoardTop - objectiveIconDimension/2,
                mObjectiveBoardLeft + mObjectiveBoardWidth /2 - objectiveIconDimension/2,
                mObjectiveBoardTop + objectiveIconDimension/2
        );

        Rect rect3 = new Rect(
                mObjectiveBoardLeft + mObjectiveBoardWidth /2 + objectiveIconDimension/2,
                mObjectiveBoardTop - objectiveIconDimension/2,
                mObjectiveBoardLeft + mObjectiveBoardWidth /2 + (3 * objectiveIconDimension/2),
                mObjectiveBoardTop + objectiveIconDimension/2
        );

        mRectObjectiveIcons.add(rect1);
        mRectObjectiveIcons.add(rect2);
        mRectObjectiveIcons.add(rect3);
    }

    public void refresh(ArrayList<ArrayList<Brick>> board) {

        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {

                Brick brick = board.get(i).get(j);

                if (brick == null) {
                    mDrawableBoardObjects.get(i).set(j, mDrawableEmptyRegion);
                    continue;
                };

                if (brick.getBrickType() == BrickType.NORMAL){
                    switch (brick.getSet().getSetType()){
                        case BOX:
                        case MILL:
                        case HALF_MILL:
                            mDrawableBoardObjects.get(i).set(j, mDrawableBrickBlue);
                            break;
                        case LINE:
                        case LINE3:
                        case LINE5:
                            mDrawableBoardObjects.get(i).set(j, mDrawableBrickOrange);
                            break;
                        case T:
                        case DIAG2:
                        case DIAG3:
                            mDrawableBoardObjects.get(i).set(j, mDrawableBrickGreen);
                            break;
                        case J:
                        case Z:
                            mDrawableBoardObjects.get(i).set(j, mDrawableBrickRed);
                            break;
                        case L:
                        case L3:
                            mDrawableBoardObjects.get(i).set(j, mDrawableBrickPink);
                            break;
                    }
                }else if (brick.getBrickType() == BrickType.COIN){
                    mDrawableBoardObjects.get(i).set(j, mDrawableBrickPink);
                }else if (brick.getBrickType() == BrickType.STAR){
                    mDrawableBoardObjects.get(i).set(j, mDrawableBrickStar);
                }
            }
        }

        invalidate();
    }

    public void popUpScore(ArrayList<DisplayData.Score> scores) {
        for (DisplayData.Score score : scores) {
            mGateScores.set(score.at, true);
            mTextScores.set(score.at, Integer.toString(score.value));
        }
    }

    private void loadPaints(){
        mPaintTest = new Paint();
        mPaintTest.setAntiAlias(true);
        mPaintTest.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintTest.setColor(0xff00ff00);
        mPaintTest.setStrokeWidth(2);
        mPaintTest.setTextSize(42);

        mPaintScoreBoard = new Paint();
        mPaintScoreBoard.setAntiAlias(true);
        mPaintScoreBoard.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintScoreBoard.setColor(0xff000000);
        mPaintScoreBoard.setStrokeWidth(2);
        mPaintScoreBoard.setTextSize(60);

        mPaintBoardBackground = new Paint();
        mPaintBoardBackground.setAntiAlias(true);
        mPaintBoardBackground.setStyle(Paint.Style.FILL);
        mPaintBoardBackground.setColor(0x99FFFFFF);

        mPaintTimeBoard = new Paint();
        mPaintTimeBoard.setAntiAlias(true);
        mPaintTimeBoard.setStyle(Paint.Style.FILL);
        mPaintTimeBoard.setColor(0xFF000000);

        mPaintScore = new Paint();
        mPaintScore.setAntiAlias(true);
        mPaintScore.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintScore.setColor(0xff993399);
        mPaintScore.setStrokeWidth(4);
        mPaintScore.setTextSize(0);

        mPaintPopup = new Paint();
        mPaintPopup.setAntiAlias(true);
        mPaintPopup.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintPopup.setColor(0xff993399);
        mPaintPopup.setStrokeWidth(4);
        mPaintPopup.setTextSize(0);

        mPaintEmpty = new Paint();
        mPaintEmpty.setAntiAlias(true);
        mPaintEmpty.setStyle(Paint.Style.STROKE);
        mPaintEmpty.setColor(0x77fcba03);
        mPaintEmpty.setStrokeWidth(2);
    }

    private void loadDrawables(){
        mDrawableBrickRed = getContext().getDrawable(R.drawable.ic_fuscia_briket);
        mDrawableBrickGreen = getContext().getDrawable(R.drawable.ic_green_briket);
        mDrawableBrickBlue = getContext().getDrawable(R.drawable.ic_blue_briket);
        mDrawableBrickPink = getContext().getDrawable(R.drawable.ic_raspberry_briket);
        mDrawableBrickOrange = getContext().getDrawable(R.drawable.ic_orange_briket);
        mDrawableBrickStar = getContext().getDrawable(R.drawable.ic_tough_briket);
        mDrawableEmptyRegion = getContext().getDrawable(R.drawable.shape);
        mDrawableScoreBoard = getContext().getDrawable(R.drawable.ic_score_board);
        mDrawableTimeBoard = getContext().getDrawable(R.drawable.ic_time_board);
        mDrawableObjectiveBoard = getContext().getDrawable(R.drawable.ic_objective_board);
    }

    private void loadAnimators() {
        mAnimatorScore = ValueAnimator.ofInt(0, 75);
        mAnimatorScore.setDuration(700);
        mAnimatorScore.setInterpolator(new BounceInterpolator());
        mAnimatorScore.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mPaintScore.setTextSize((Integer) valueAnimator.getAnimatedValue());
                invalidate();
            }
        });

        mAnimatorScore.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                for (int i = 0; i < mGateScores.size(); i++) {
                    mGateScores.set(i, false);
                }
                invalidate();
                mRandomNumber = mRandomGenerator.nextInt(1000);
            }
        });

        mAnimatorEvent = ValueAnimator.ofInt(0, 120);
        mAnimatorEvent.setDuration(1000);
        mAnimatorEvent.setInterpolator(new BounceInterpolator());
        mAnimatorEvent.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mPaintPopup.setTextSize((Integer) valueAnimator.getAnimatedValue());
                invalidate();
            }
        });

        mAnimatorEvent.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mGateCombo = false;
                invalidate();
            }
        });
    }

    public void updateScore(String score){
        mScoreBoardText = score;
    }

    public void updateTime(String time){
        mTimeBoardText = time;
    }

    public void updateObjective(String objective){
        mObjectiveBoardText = objective;
    }

    public void popUp(DisplayData.PopUpEvent popUpEvent){
        mGateCombo = true;
    }
}
