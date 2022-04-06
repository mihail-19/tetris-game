package com.teslenko.tetris.android;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.util.Log;

import java.io.IOException;


public class SoundPlayer {
    public static final String ACTION_OK_FILE = "/";
    private MediaPlayer actionOkPlayer;
    private MediaPlayer actionDeniedPlayer;
    private MediaPlayer newLevelPlayer;
    private MediaPlayer lineErasedPlayer;
    private Vibrator vibrator;
    public static boolean isSoundOn = true;
    public static boolean isVibrationOn = true;
    private Context context;
    private SoundPlayer(Context context){
        this.context = context;
        actionOkPlayer = MediaPlayer.create(context, R.raw.ok);
        actionDeniedPlayer = MediaPlayer.create(context, R.raw.denied);
        newLevelPlayer = MediaPlayer.create(context, R.raw.level);
        lineErasedPlayer = MediaPlayer.create(context, R.raw.erased);
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

    }
    public static SoundPlayer getInstance(Context context){
        if(Inner.instance == null){
            Inner.instance = new SoundPlayer(context);
        }
        return Inner.instance;
    }
    private static class Inner{
        private static SoundPlayer instance;
    }
    public void playActionOk(){
        try {
            if(actionOkPlayer.isPlaying()){
                actionOkPlayer.stop();
                actionOkPlayer.prepare();
            }
            if(isSoundOn) {
                actionOkPlayer.start();
            }
        } catch (Exception e) {
            Log.e("tetrissound", e.toString());
            try {
                actionOkPlayer = MediaPlayer.create(context, R.raw.ok);
            } catch (Exception ei){
                Log.e("tetrissound", ei.toString());
            }
        }
        try {
            if (isVibrationOn) {
                vibrate();
            }
        } catch (Exception e) {
            Log.e("tetrissound", e.toString());
        }

    }

    public void playActionDenied(){
        try {
            if (isSoundOn) {
                actionDeniedPlayer.start();
            }
            if (isVibrationOn) {
                vibrate();
            }
        } catch (Exception e){
            Log.e("tetrissound", e.toString());
        }
    }

    public void playNewLevel(){
        try {
            if (isSoundOn) {
                newLevelPlayer.start();
            }
            if (isVibrationOn) {
                vibrate();
            }
        } catch(Exception e){
            Log.e("tetrissound", e.toString());
        }
    }

    public void playLineErased(){
        try {
            if (isSoundOn) {
                lineErasedPlayer.start();
            }
        } catch(Exception e){
            Log.e("tetrissound", e.toString());
        }
    }
    public void playeGameOver(){
        playActionDenied();
        //TODO
        try {
            if (isVibrationOn) {
                vibrate();
            }
        }catch(Exception e){
            Log.e("tetrissound", e.toString());
        }
    }

    public void vibrate(){
        vibrator.vibrate(50);
    }

    public void setSoundOff(){
        isSoundOn = false;
    }
    public void setSoundOn(){
        isSoundOn = true;
    }
    public void setVibrationOff(){
        isVibrationOn = false;
    }
    public void setVibrationOn(){
        isVibrationOn = true;
    }
    public boolean isSoundOn(){
        return  isSoundOn;
    }
    public boolean isVibrationOn(){
        return isVibrationOn;
    }
}
