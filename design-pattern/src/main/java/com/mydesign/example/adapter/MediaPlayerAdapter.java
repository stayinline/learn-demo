package com.mydesign.example.adapter;

public class MediaPlayerAdapter implements MediaPlayer {

    private AdvancedMediaPlayer advancedMediaPlayer;

    public MediaPlayerAdapter(String audioType) {
        if ("mp3".equals(audioType)) {
            advancedMediaPlayer = new Mp3Player();
        } else if ("mp4".equals(audioType)) {
            advancedMediaPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if ("mp3".equals(audioType)) {
            advancedMediaPlayer.playMp3(fileName);
        } else if ("mp4".equals(audioType)) {
            advancedMediaPlayer.playMp4(fileName);
        }
    }
}
