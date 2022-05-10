package com.mydesign.example.adapter;

public class AutoPlayer implements MediaPlayer {

    @Override
    public void play(String audioType, String fileName) {
        MediaPlayerAdapter mediaPlayerAdapter;
        if ("mp3".equals(audioType) || "mp4".equals(audioType)) {
            mediaPlayerAdapter = new MediaPlayerAdapter(audioType);
            mediaPlayerAdapter.play(audioType, fileName);
        } else if ("rar".equals(audioType)) {
            System.out.println("正在使用内嵌播放器播放" + audioType + "格式的文件：" + fileName);
        } else {
            System.out.println(audioType + "格式的文件" + fileName + "暂时不支持");
        }
    }
}
