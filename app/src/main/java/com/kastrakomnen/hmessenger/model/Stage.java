package com.kastrakomnen.hmessenger.model;

import com.kastrakomnen.hmessenger.model.set.Agent;
import com.kastrakomnen.hmessenger.model.set.FormationType;
import com.kastrakomnen.hmessenger.model.stat.DistributionType;

import java.util.ArrayList;

public class Stage {

    /**
     * A number unique for every stage*/
    private int id;

    /**
     * A human readable name for the stage */
    private String name;

    /**
     * A number indicates an orders in stages*/
    private int index;

    /**
     * Highest score earned so far in this stage */
    private int highScore;

    /**
     * Current score in current game */
    private int score;

    /**
     * Defines the difficulty of the game */
    private int level;

    /***
     * Time amount during which game continue */
    private long stageDuration;

    /**
     * number of pattern or pattern types which will affect level */
    private ArrayList<FormationType> formationTypes;

    /**
     * define type of distribution for element generation */
    private DistributionType distributionType;

    /**
     * This defines the condition in case of met, game will be end and won */
    private ArrayList<WinCondition> winConditions;

    /**
     * A short information about this stage */
    private String brief;

    /**
     * Indicates whether this stage can be played */
    private boolean isLocked;

    /**
     * Indicates whether this stage can be played */
    private boolean isCompleted;

    private ArrayList<Agent> agents;

    public void setAgents(ArrayList<Agent> agents) {
        this.agents = agents;
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public DistributionType getDistributionType(){
        return distributionType;
    }

    public void setDistributionType(DistributionType distributionType) {
        this.distributionType = distributionType;
    }

    public ArrayList<WinCondition> getWinConditions() {
        return winConditions;
    }

    public void setWinConditions(ArrayList<WinCondition> winConditions) {
        this.winConditions = winConditions;
    }

    public void resetWinConditions(){
        for (WinCondition winCondition : winConditions) {
            winCondition.reset();
        }
    }

    public ArrayList<FormationType> getFormationTypes(){
        return formationTypes;
    }

    public void setFormationTypes(ArrayList<FormationType> formationTypes) {
        this.formationTypes = formationTypes;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getBrief() {
        return brief;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setStageDuration(long stageDuration) {
        this.stageDuration = stageDuration;
    }

    public long getStageDuration() {
        return stageDuration;
    }
}
