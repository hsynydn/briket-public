package com.example.hmessenger.logic;

import java.io.IOException;

//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;
//import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.UnsupportedAudioFileException;

public class Music{

//    private Clip clip;

    public Music(String url_param)
            throws
//            UnsupportedAudioFileException,
            IOException
//            LineUnavailableException
    {

//        AudioInputStream audioIn = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource(url_param));
//        clip = AudioSystem.getClip();
//        clip.open(audioIn);
    }

    public void start(){
//        clip.start();
//        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() throws NullPointerException{

//        clip.stop();
    }
}
