package com.songjz.componet;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Music implements Runnable{

    private Thread thread = new Thread(this);
    private boolean isPlay = true;
    Player player = new Player(new FileInputStream(new File(StaticComponent.musicPath + "music.wav")));


    public Music() throws FileNotFoundException, JavaLayerException {
        thread.start();
    }

    public boolean isPlay() {
        return isPlay;
    }

    public void setPlay(boolean play) {
        isPlay = play;
    }

    @Override
    public void run() {
        try {
            if (isPlay) {
                player.play();
            }
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }
}
