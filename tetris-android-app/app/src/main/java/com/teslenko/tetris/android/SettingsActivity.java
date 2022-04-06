package com.teslenko.tetris.android;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {
    Button resumeGame;
    Button stopGame;
    Switch soundSwitch;
    Switch vibrationSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        prepareButtons();
        checkIfGameFinished();
        soundSwitch.setOnCheckedChangeListener((cb, b) -> {
            SoundPlayer.isSoundOn = soundSwitch.isChecked();
        });
        vibrationSwitch.setOnCheckedChangeListener((cb, b) -> {
            SoundPlayer.isVibrationOn = vibrationSwitch.isChecked();
        });

        resumeGame.setOnClickListener((v) -> {
            resume();
        });
        if(GameFieldActivity.gameRestoration == null || GameFieldActivity.gameRestoration.getGameField().isFinished()){
            Intent intent = new Intent(this, GameFieldActivity.class);
            startActivity(intent);
        }
        stopGame.setOnClickListener((e) -> {
            Log.i("tetrisaction", "stop game");
            GameFieldActivity.gameRestoration = null;
            finish();
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                if(GameFieldActivity.gameRestoration != null  &&
                        GameFieldActivity.gameRestoration.getGameField() != null &&
                        !GameFieldActivity.gameRestoration.getGameField().isFinished()) {
                    resume();
                } else {
                    finish();
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(callback);
    }

    private void prepareButtons(){
        resumeGame = findViewById(R.id.resume);
        stopGame = findViewById(R.id.stop);
        soundSwitch = findViewById(R.id.sound);
        vibrationSwitch = findViewById(R.id.vibration);
    }
    private void resume(){
        Log.i("tetrisaction", "resume game");
        Intent intent = new Intent(this, GameFieldActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkIfGameFinished();
    }

    private void checkIfGameFinished(){
        if(GameFieldActivity.gameRestoration == null
                ||  GameFieldActivity.gameRestoration.getGameField() == null
                || GameFieldActivity.gameRestoration.getGameField().isFinished()){
            resumeGame.setEnabled(false);
        } else {
            resumeGame.setEnabled(true);
        }
    }
}