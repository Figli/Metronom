package com.figli.metronom;


import android.app.Activity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * Created by Figli on 21.03.2016.
 */
public class ImageBPM extends Activity implements Animation.AnimationListener {

    ImageView indicator;
    Animation animationBlink = new AlphaAnimation(1, 0);

    public ImageBPM(ImageView indicator) {
        this.indicator = indicator;
    }

    public ImageBPM() {

    }

    public void imageBlink() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                animationBlink.setDuration(300);
                animationBlink.setInterpolator(new LinearInterpolator());
                animationBlink.setRepeatCount(0);
                animationBlink.setRepeatMode(Animation.REVERSE);
                indicator.startAnimation(animationBlink);
            }
        });
    }

    public void stopBlink() {
        animationBlink.cancel();
    }




    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        if (animation == animationBlink) {

        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
