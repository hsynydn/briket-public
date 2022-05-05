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
    private AppCompatActivity       context;

    public void importObject(Player player){
        this.player = player;
    }

    public DisplayUnitController(AppCompatActivity context, ArrayList<ImageView> gridPane){
        this.gridPane   = gridPane;
        this.eventStack = new Stack<Event>();
        this.context    = context;
        this.gameOver   = context.findViewById(R.id.game_over);
    }

    public void pushEvent(Event e){
        eventStack.push(e);
    }

    public void refreshMonitor(ArrayList<Cell> gridValues)throws NullPointerException{

        for(Cell cell : gridValues){

            int linear_index = gridValues.indexOf(cell);
            ImageView view = gridPane.get(linear_index);

            if(cell.isSet()){
//                view.setBackgroundColor(cell.getColor());
                try {
                    view.setImageResource(R.drawable.brick_red);
                }catch (Exception e){
                    Log.i(TAG, "Exception caught ── " + e.toString());
                }
                Log.i(TAG, "view.setImageResource(R.drawable.brick_red)");
            }else{
                Log.i(TAG, "view.setImageResource(0)");
//                view.setBackgroundColor(0xFFFFFFFF);
                try {
                    view.setImageResource(0);
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
}

