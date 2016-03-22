package com.figli.metronom;

import android.hardware.Camera;

import android.hardware.Camera.Parameters;

/**
 * Created by Figli on 21.03.2016.
 */
public class FlashBPM {

    private Camera camera;
    Parameters params;


    public FlashBPM() {

    }

    public void flashStrobo(int delay) {
        setFlashlightOn();
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setFlashlightOff();
    }


    public void setFlashlightOn() {
        camera = Camera.open();
        params = camera.getParameters();
        params.setFlashMode(Parameters.FLASH_MODE_TORCH);
        camera.setParameters(params);
        camera.startPreview();
    }

    public void setFlashlightOff() {
        camera.stopPreview();
        camera.release();
        camera = null;
    }
}
