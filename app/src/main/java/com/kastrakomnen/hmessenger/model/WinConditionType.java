package com.kastrakomnen.hmessenger.model;

public enum WinConditionType {

    /** Line Up bricks */
    LINEUP_BASIC,

    /** Line Up star bricks */
    LINEUP_STAR,

    /** Reach minimum score in the given time */
    AGAINST_TIME,

    /** Complete number of basic bricks combo */
    BASIC_COMBO,

    /** Complete number of star bricks combo */
    STAR_COMBO,

    /** Collect number of coin */
    COLLECT_COIN
}