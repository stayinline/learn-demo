package com.mydesign.example.adapter;

public class Mp3Player implements AdvancedMediaPlayer {


    @Override
    public void playMp4(String fileName) {

    }

    @Override
    public void playMp3(String fileName) {
        System.out.println("正在播放mp3:" + fileName);
    }
}
