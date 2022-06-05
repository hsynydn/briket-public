package com.kastrakomnen.hmessenger.view;

import android.graphics.Rect;

public class ProgressCard {

    private boolean locked;
    private int hi_score;
    private int earn_star;
    private int chapter_number;

    public ProgressCard(boolean locked){
        this.locked = locked;
    }

    public boolean isLocked(){
        return locked;
    }
}
