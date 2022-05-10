package com.mydesign.example.adapter;


/**
 * 实现一个媒体播放器：
 * 播放不同格式的媒体文件
 */
public class MediaAdapterDomo {

    public static void main(String[] args) {
        String mp3 = "夜曲.mp3";
        MediaPlayer autoPlayer = new AutoPlayer();
        autoPlayer.play("mp3", mp3);

        String mp4 = "稻香.mp4";
        autoPlayer.play("mp4", mp4);

        String rar = "青花瓷.rar";
        autoPlayer.play("rar", rar);

        String avi = "七里香.avi";
        autoPlayer.play("avi", avi);

        /*
         * 正在播放mp3:夜曲.mp3
         * 正在播放mp4:稻香.mp4
         * 正在使用内嵌播放器播放rar格式的文件：青花瓷.rar
         * avi格式的文件七里香.avi暂时不支持
         */

    }
}
