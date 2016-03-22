package com.figli.metronom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button startStopButton;

    Metronom metronom;

    ImageView indicator;
    ImageView vibroButton;
    ImageView flashButton;
    ImageView soundButton;

    TextView vibroText;
    TextView flashText;
    TextView soundText;

    EditText editBPM;

    SeekBar seekBar;

    int bpm;
    int minute = 60000;
    int delay;

    boolean push = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initResourses();
        initStartStopButton();

        getSeekBar();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.vibro_button:
                        getPushVibroButton();
                        break;
                    case R.id.flash_button:
                        getPushFlashButton();
                        break;
                    case R.id.sound_button:
                        getPushSoundButton();
                        break;
                    default:
                        break;
                }
            }
        };
        vibroButton.setOnClickListener(onClickListener);
        flashButton.setOnClickListener(onClickListener);
        soundButton.setOnClickListener(onClickListener);
    }

    public int getBpm() {
        if (editBPM.getText().toString().equals("") || editBPM.getText().toString().equals("0")) {
            bpm = 60;
        } else {
            bpm = Integer.parseInt(editBPM.getText().toString());
        }
        return bpm;
    }

    private int getDelay() {
        delay = minute / getBpm();
        return delay;
    }

    private void initResourses() {
        startStopButton = (Button) findViewById(R.id.button_start);
        indicator = (ImageView) findViewById(R.id.indicator);
        vibroButton = (ImageView) findViewById(R.id.vibro_button);
        flashButton = (ImageView) findViewById(R.id.flash_button);
        soundButton = (ImageView) findViewById(R.id.sound_button);
        vibroText = (TextView) findViewById(R.id.vibro_text);
        flashText = (TextView) findViewById(R.id.flash_text);
        soundText = (TextView) findViewById(R.id.sound_text);
        editBPM = (EditText) findViewById(R.id.edit_bpm);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
    }

    public void getSeekBar() {

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int seekBarProgress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarProgress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editBPM.setText(String.valueOf(seekBarProgress));
            }
        });
    }

    private void getPushVibroButton() {
        if (Metronom.isVibration) {
            Metronom.isVibration = false;
            vibroButton.setImageResource(R.drawable.vibro_g);
            vibroText.setTextColor(getResources().getColor(R.color.colorTextGrey));
        } else {
            Metronom.isVibration = true;
            vibroButton.setImageResource(R.drawable.vibro_w);
            vibroText.setTextColor(getResources().getColor(R.color.colorText));
        }
    }

    private void getPushFlashButton() {
        if (Metronom.isFlash) {
            Metronom.isFlash = false;
            flashButton.setImageResource(R.drawable.flash_g);
            flashText.setTextColor(getResources().getColor(R.color.colorTextGrey));
        } else {
            Metronom.isFlash = true;
            flashButton.setImageResource(R.drawable.flash_v);
            flashText.setTextColor(getResources().getColor(R.color.colorText));
        }
    }

    private void getPushSoundButton() {
        if (Metronom.isSound) {
            Metronom.isSound = false;
            soundButton.setImageResource(R.drawable.sound_g);
            soundText.setTextColor(getResources().getColor(R.color.colorTextGrey));
        } else {
            Metronom.isSound = true;
            soundButton.setImageResource(R.drawable.sound);
            soundText.setTextColor(getResources().getColor(R.color.colorText));
        }
    }

    private void initStartStopButton() {

        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (push) {
                    startStopButton.setText(R.string.stop);
                    push = false;
                    if (metronom == null) {
                        metronom = new Metronom(getApplicationContext(), indicator, getDelay());
                        metronom.play();
                    }
                } else {
                    metronom.stop();
                    startStopButton.setText(R.string.start);
                    push = true;
                    metronom = null;
                }
            }
        });
    }
}

