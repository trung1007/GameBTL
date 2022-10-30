package com.example.bomberman.Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];
    public Sound(){
        soundURL[0] = getClass().getResource("/Sounds/Für-Elise-MP3.wav");
        soundURL[1] = getClass().getResource("/Sounds/mixkit-crickets-and-insects-in-the-wild-ambience-39.wav");
        soundURL[2] = getClass().getResource("/sound/boom_bang.wav");
        soundURL[3] = getClass().getResource("/sound/move.wav");
        soundURL[4] = getClass().getResource("/sound/background.wav");
    }
    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch (Exception e){

        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
