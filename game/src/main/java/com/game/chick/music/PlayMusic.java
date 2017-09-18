package com.game.chick.music;

import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JApplet;

public class PlayMusic {
    public static AudioClip loadSound(String filename) {
        URL url = null;
        try {
            url = new URL("file:" + filename);
        } catch (MalformedURLException e) {
        }
        return JApplet.newAudioClip(url);
    }

    public void play() {
        AudioClip christmas = loadSound("music/bg.wav");
        christmas.play();
    }

    public static void main(String[] args) {
        new PlayMusic().play();
    }
}