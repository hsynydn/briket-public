package com.kastrakomnen.hmessenger.logic;

import java.util.ArrayList;


import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.kastrakomnen.hmessenger.PlayBoardView;
import com.kastrakomnen.hmessenger.R;

public class DisplayUnitController implements Variables
{
    private static final String TAG = "DisplayUnitController";

    public  PlayBoardView           playBoardView;
    public  ArrayList<Paint>        gridPane;
    private final ImageView         gameOver;
    private ImageView               nextPatternPanel;
    private AppCompatActivity       context;
    private android.os.Handler      handler;
    private TextView                scoreTextView;

    public DisplayUnitController(
            AppCompatActivity context,
            PlayBoardView playBoardView,
            android.os.Handler handler
    ){
        this.playBoardView      = playBoardView;
        this.gridPane           = playBoardView.paints;
        this.context            = context;
        this.gameOver           = context.findViewById(R.id.rot_elem);
        this.handler            = handler;
        this.scoreTextView      = context.findViewById(R.id.tv_score_content);
    }

    public void refreshMonitor(ArrayList<Cell> gridValues)throws NullPointerException{

        String s = "";
        int i = 1;
        for(Cell c : gridValues){

            if (c.isSet()){
                s+="1 - ";
            }else{
                s+="0 - ";
            }

            if (i%12==0){
                s+="\n";
            }
            i++;
        }
        Log.i(TAG, s);

        for(Cell cell : gridValues){

            Paint paint = playBoardView.paints.get(gridValues.indexOf(cell));

            if(cell.isSet()){
                try {
                    switch (cell.getColor()){
                        case COLOR_ORANGE:
//                            paint.setStyle(Paint.Style.FILL);
//                            paint.setColor(0xffff9933);
                            playBoardView.drawables.set(gridValues.indexOf(cell), playBoardView.orange);
                            break;
                        case COLOR_BLUE:
//                            paint.setStyle(Paint.Style.FILL);
//                            paint.setColor(0xff0033cc);
                            playBoardView.drawables.set(gridValues.indexOf(cell), playBoardView.blue);
                            break;
                        case COLOR_GREEN:
//                            paint.setStyle(Paint.Style.FILL);
//                            paint.setColor(0xff00cc00);
                            playBoardView.drawables.set(gridValues.indexOf(cell), playBoardView.green);
                            break;
                        case COLOR_RED:
//                            paint.setStyle(Paint.Style.STROKE);
//                            paint.setColor(0xffcc3300);
                            playBoardView.drawables.set(gridValues.indexOf(cell), playBoardView.red);
                            break;
                    }

                }catch (Exception e){
                    Log.i(TAG, "Exception caught ── " + e.toString());
                }
            }else{
//                paint.setStyle(Paint.Style.STROKE);
//                paint.setColor(0xffffcccc);
                playBoardView.drawables.set(gridValues.indexOf(cell), playBoardView.empty);
            }
        }

        playBoardView.invalidate();
    }

    void burnFx(ArrayList<Integer> burn_indices){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=0; i<4; i++){
                        for (Integer index : burn_indices) {
                            playBoardView.animations_flags.get(index).set(i, true);
                            playBoardView.animations_flags.get(index).set(i + 4, true);
                            playBoardView.animations_flags.get(index).set(i + 8, true);
                            playBoardView.animations_flags.get(index).set(i + 12, true);
                        }
                        playBoardView.invalidate();
                        Thread.sleep(100);
                        for (Integer index : burn_indices) {
                            playBoardView.animations_flags.get(index).set(i, false);
                            playBoardView.animations_flags.get(index).set(i+4, false);
                            playBoardView.animations_flags.get(index).set(i+8, false);
                            playBoardView.animations_flags.get(index).set(i+12, false);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void actionOnStart(){
//        startButton.setVisible(false);
//        gameOver.setVisible(false);
    }


    public void setScore(int sequence){
        char[] score = Integer.toString(sequence).toCharArray();
        scoreTextView.setText(score, 0 , score.length);
    }

    public void visibleGameOver(){
        gameOver.setVisibility(View.VISIBLE);
    }

    public void setNextPattern(PatternType patternType){
        Log.i(TAG, "setNextPattern");
        switch (patternType){
            case BoxPattern:
                Log.i(TAG, "setNextPattern::BoxPattern");

                for (int i = 0; i<16; i++){
                    playBoardView.drawables2.set(i, playBoardView.blank);
                }
                playBoardView.drawables2.set(5, playBoardView.blue);
                playBoardView.drawables2.set(6, playBoardView.blue);
                playBoardView.drawables2.set(9, playBoardView.blue);
                playBoardView.drawables2.set(10, playBoardView.blue);
                playBoardView.invalidate();

                break;
            case LinePattern:
                Log.i(TAG, "setNextPattern::LinePattern");

                for (int i = 0; i<16; i++){
                    playBoardView.drawables2.set(i, playBoardView.blank);
                }
                playBoardView.drawables2.set(1, playBoardView.orange);
                playBoardView.drawables2.set(5, playBoardView.orange);
                playBoardView.drawables2.set(9, playBoardView.orange);
                playBoardView.drawables2.set(13, playBoardView.orange);
                playBoardView.invalidate();

                break;
            case LPattern:
                Log.i(TAG, "setNextPattern::LPattern");

                for (int i = 0; i<16; i++){
                    playBoardView.drawables2.set(i, playBoardView.blank);
                }
                playBoardView.drawables2.set(5, playBoardView.green);
                playBoardView.drawables2.set(9, playBoardView.green);
                playBoardView.drawables2.set(13, playBoardView.green);
                playBoardView.drawables2.set(14, playBoardView.green);
                playBoardView.invalidate();

                break;
            case TPattern:
                Log.i(TAG, "setNextPattern::TPattern");

                for (int i = 0; i<16; i++){
                    playBoardView.drawables2.set(i, playBoardView.blank);
                }
                playBoardView.drawables2.set(5, playBoardView.red);
                playBoardView.drawables2.set(6, playBoardView.red);
                playBoardView.drawables2.set(7, playBoardView.red);
                playBoardView.drawables2.set(10, playBoardView.red);
                playBoardView.invalidate();

                break;
        }
    }
}

