package com.figli.metronom;

import android.content.Context;
import android.os.Vibrator;

/**
 * Created by Figli on 21.03.2016.
 */
public class VibroBPM {


    Vibrator vibe;

    public VibroBPM(Context context) {
        this.vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }



    public Vibrator vibe() {
        return vibe;
    }


}
