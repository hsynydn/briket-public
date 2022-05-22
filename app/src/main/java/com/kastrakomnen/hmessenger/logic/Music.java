package com.kastrakomnen.hmessenger.logic;

import android.content.Context;
import android.media.MediaPlayer;

import java.io.IOException;

public class Music{

    MediaPlayer mediaPlayer;

    public Music(Context context, int audio_uri)
            throws
            IOException
    {
        mediaPlayer = MediaPlayer.create(context, audio_uri);
    }

    public void start(){
        mediaPlayer.start(); // This will play continuously
    }

    public void stop() throws NullPointerException{
        mediaPlayer.stop();
    }
}
