package com.kastrakomnen.hmessenger.model.stat;

import java.util.ArrayList;

public class GameStatCollector {

    public interface ScoreListener{
        void onScoreEvent(int score);
    }

    public interface ComboListener{
        void onComboEvent(int combo);
    }

    public interface TimeListener{
        void onTimeEvent(int time);
    }

    private final ArrayList<GameStatCollector.ScoreListener> scoreListeners;
    private final ArrayList<GameStatCollector.ComboListener> comboListeners;
    private final ArrayList<GameStatCollector.TimeListener> timeListeners;

    public GameStatCollector(){
        scoreListeners = new ArrayList<>();
        comboListeners = new ArrayList<>();
        timeListeners = new ArrayList<>();
    }

    public void addScoreListener(GameStatCollector.ScoreListener scoreListener){
        scoreListeners.add(scoreListener);
    }

    public void addComboListener(GameStatCollector.ComboListener comboListener){
        comboListeners.add(comboListener);
    }

    public void addTimeListener(GameStatCollector.TimeListener timeListener){
        timeListeners.add(timeListener);
    }

    public void setScore(int score){
        for (GameStatCollector.ScoreListener scoreListener: scoreListeners) {
            scoreListener.onScoreEvent(score);
        }
    }

    public void setCombo(int combo){
        for (GameStatCollector.ComboListener comboListener: comboListeners) {
            comboListener.onComboEvent(combo);
        }
    }

    public void setTime(int time){
        for (GameStatCollector.TimeListener timeListener: timeListeners) {
            timeListener.onTimeEvent(time);
        }
    }
}
