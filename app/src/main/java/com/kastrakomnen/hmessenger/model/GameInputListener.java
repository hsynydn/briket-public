package com.kastrakomnen.hmessenger.model;

public interface GameInputListener {

    /**
     * A request to the game to move the current element 1 square right
     */
    public void onMoveRight(int amount);

    /**
     * A request to the game to move the current element 1 square left
     */
    public void onMoveLeft(int amount);

    /**
     * A request to the game to move the current element 1 square down
     */
    public void onMoveDown();

    /**
     * A request to the game to rotate the current element
     */
    public void onRotate();
}
