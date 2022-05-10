package com.mydesign.example.adapter;

public class Mp4Player implements AdvancedMediaPlayer {


    @Override
    public void playMp4(String fileName) {
        System.out.println("正在播放mp4:" + fileName);

    }

    @Override
    public void playMp3(String fileName) {
    }
}
