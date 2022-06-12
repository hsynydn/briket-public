package com.kastrakomnen.hmessenger.model;

import java.util.ArrayList;

public class Stage {

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
    private WinCondition winCondition;

    /**
     * A short information about this stage */
    private String brief;

    /**
     * Indicates whether this stage can be played */
    private boolean isLocked;

    public DistributionType getDistributionType(){
        return distributionType;
    }

    public WinCondition getWinCondition(){
        return winCondition;
    }

    public ArrayList<FormationType> getFormationTypes(){
        return formationTypes;
    }
}
