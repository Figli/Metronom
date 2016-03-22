package com.figli.metronom;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

/**
 * Created by Figli on 19.03.2016.
 */
public class VoiceBPM {

    Context context;

    MediaPlayer mediaPlayer;

    public VoiceBPM(Context context) {
        this.context = context;

    }

    public void playSound() {

        try {
            AssetFileDescriptor assetFileDescriptor = context.getAssets().openFd("beat.mp3");
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),
                    assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();

                }


            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}