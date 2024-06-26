package com.kastrakomnen.hmessenger.model.display;

import com.kastrakomnen.hmessenger.model.StageStatus;
import com.kastrakomnen.hmessenger.model.set.Brick;
import com.kastrakomnen.hmessenger.model.Position;
import com.kastrakomnen.hmessenger.model.Publisher;

import java.util.ArrayList;

public interface DisplayUnitController extends Publisher {

    void create(DisplayData.Board board);
    void create(DisplayData.Brick brick, Position at);

    void move(DisplayData.Brick brick, Position from, Position to);

    void refresh(ArrayList<ArrayList<Brick>> board);
    void refreshWitchAnimation(ArrayList<ArrayList<Brick>> board, ArrayList<DisplayData.DeletionAnimation> deletionAnimations);

    void move(
            ArrayList<DisplayData.Brick> brickArrayList,
            ArrayList<Position> fromPositions,
            ArrayList<Position> toPositions
            );

    void rotate(
            ArrayList<DisplayData.Brick> brickArrayList,
            ArrayList<Position> fromPositions,
            ArrayList<Position> toPositions
            );

    void remove(DisplayData.Brick brick, Position at);

    void removeAndRefresh(
            ArrayList<DisplayData.Brick> brickArrayList,
            ArrayList<Position> atPositions,
            ArrayList<Position> board
            );

    void refresh(
            ArrayList<DisplayData.Brick> brickArrayList,
            ArrayList<Position> atPositions
            );

    void startFast();
    void startDelay(int delay);
    void updateInfo();
    void end(DisplayData.Status status);

    void gainScore(ArrayList<DisplayData.Score> scores);
    void updateObjective(int objective);
    void setScore(int score);

    void popUp(DisplayData.PopUpEvent popUpEvent);
}
