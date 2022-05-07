package com.example.hmessenger.logic;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Stack;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.hmessenger.R;

public class DisplayUnitController
        implements
        Variables
{
    private static final String TAG = "DisplayUnitController";

    public  ArrayList<ImageView>    gridPane;
    public  Stack<Event>            eventStack;
    private Player                  player;
    private final ImageView         gameOver;
    private ImageView               nextPatternPanel;
    private AppCompatActivity       context;

    public void importObject(Player player){
        this.player = player;
    }

    public DisplayUnitController(AppCompatActivity context, ArrayList<ImageView> gridPane, ImageView nextPatternPanel){
        this.gridPane           = gridPane;
        this.eventStack         = new Stack<Event>();
        this.context            = context;
        this.gameOver           = context.findViewById(R.id.game_over);
        this.nextPatternPanel   = nextPatternPanel;
    }

    public void pushEvent(Event e){
        eventStack.push(e);
    }

    public void refreshMonitor(ArrayList<Cell> gridValues)throws NullPointerException{

        for(Cell cell : gridValues){

            int linear_index = gridValues.indexOf(cell);
            ImageView view = gridPane.get(linear_index);

            if(cell.isSet()){
                try {
                    switch (cell.getColor()){
                        case COLOR_ORANGE:
                            view.setImageResource(R.drawable.shape_orange);
                            break;
                        case COLOR_BLUE:
                            view.setImageResource(R.drawable.shape_blue);
                            break;
                        case COLOR_GREEN:
                            view.setImageResource(R.drawable.shape_green);
                            break;
                        case COLOR_RED:
                            view.setImageResource(R.drawable.shape_pinky);
                            break;
                    }

                }catch (Exception e){
                    Log.i(TAG, "Exception caught ── " + e.toString());
                }
            }else{
                try {
                    view.setImageResource(R.drawable.shape);
                }catch (Exception e){
                    Log.i(TAG, "Exception caught ── " + e.toString());
                }
            }
        }
    }

    public void addToActionStack(Event action){
        eventStack.add(action);
    }

    public void emptyActionStack(){
        eventStack.removeAllElements();
    }

    public Stack<Event> getStack(){
        return eventStack;
    }

    public void actionOnStart(){
        player.startGame();
//        startButton.setVisible(false);
//        gameOver.setVisible(false);
    }

    public void actionOnExit(){
        player.exitGame();
    }

    public void setScore(int sequence){
        player.setScore(sequence*12);
    }

    public void visibleGameOver(){
        gameOver.setVisibility(View.VISIBLE);
    }

    public void moveRight(){

    }

    public void moveLeft(){

    }

    public void moveDown(){

    }

    public void rotate(){

    }

    public void setNextPattern(PatternType patternType){
        Log.i(TAG, "setNextPattern");
        switch (patternType){
            case BoxPattern:
                Log.i(TAG, "setNextPattern::BoxPattern");
                nextPatternPanel.setImageDrawable(context.getDrawable(R.drawable.box_pattern));
                break;
            case LinePattern:
                Log.i(TAG, "setNextPattern::LinePattern");
                nextPatternPanel.setImageDrawable(context.getDrawable(R.drawable.line_pattern));
                break;
            case LPattern:
                Log.i(TAG, "setNextPattern::LPattern");
                nextPatternPanel.setImageDrawable(context.getDrawable(R.drawable.l_pattern));
                break;
            case TPattern:
                Log.i(TAG, "setNextPattern::TPattern");
                nextPatternPanel.setImageDrawable(context.getDrawable(R.drawable.t_pattern));
                break;
        }
    }
}

