package com.kastrakomnen.hmessenger.model.stat;

public class GameStatistics {

    private int score;
    private int comboX2;
    private int comboX3;
    private int comboX4;
    private int lineUp;
    private int elapsedTime;
    private int coin;
    private int diamond;

    public void setScore(int score) {
        this.score = score;
    }

    public void setComboX2() {
        this.comboX2++;
    }

    public void setComboX3() {
        this.comboX3++;
    }

    public void setComboX4() {
        this.comboX4++;
    }

    public void setLineUp(int lineUp) {
        this.lineUp = lineUp;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    public int getScore() {
        return score;
    }

    public int getComboX2() {
        return comboX2;
    }

    public int getComboX3() {
        return comboX3;
    }

    public int getComboX4() {
        return comboX4;
    }

    public int getLineUp() {
        return lineUp;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public int getCoin() {
        return coin;
    }

    public int getDiamond() {
        return diamond;
    }
}
