package com.kastrakomnen.hmessenger.model;

import java.util.ArrayList;

public interface DisplayUnitController {

    public void createMap(int h, int w);
    public void startFast();
    public void startDelay(int delay);
    public void move(ArrayList<Position> from, ArrayList<Position> to);
    public void remove(ArrayList<Position> from);
    public void updateInfo();
    public void end();

}
