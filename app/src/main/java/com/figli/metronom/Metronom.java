package com.figli.metronom;

import android.content.Context;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Figli on 21.03.2016.
 */
public class Metronom {

    Context context;
    VoiceBPM voiceBPM;
    VibroBPM vibroBPM;
    FlashBPM flashBPM;
    ImageBPM imageBPM;

    int delay;

    public static boolean isVibration = true;
    public static boolean isFlash = true;
    public static boolean isSound = true;

    public Metronom(Context context, ImageView indicator, int delay) {
        this.context = context;
        this.delay = delay;
        voiceBPM = new VoiceBPM(context);
        vibroBPM = new VibroBPM(context);
        flashBPM = new FlashBPM();
        imageBPM = new ImageBPM(indicator);
    }

    Timer timer = new Timer();

    public void play() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (isSound) {
                    voiceBPM.playSound();
                }

                if (isFlash) {
                    flashBPM.flashStrobo(60);
                }

                if (isVibration) {
                    vibroBPM.vibe().vibrate(60);
                }

                imageBPM.imageBlink();
            }
        };
        timer.scheduleAtFixedRate(timerTask, delay, delay);
    }

    public void stop() {
        timer.cancel();
        imageBPM.stopBlink();
    }
}
