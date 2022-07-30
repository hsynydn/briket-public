package com.kastrakomnen.hmessenger.model;

public class Preferences {

    private boolean music;
    private boolean sound;

    Preferences(){

    }

    public void setMusic(boolean music) {
        this.music = music;
    }

    public boolean isMusic() {
        return music;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public boolean isSound() {
        return sound;
    }
}
