package com.teslenko.tetris.android;

import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.teslenko.tetris.model.GameProcess;

public class TetrisControlListener implements  View.OnTouchListener{
    public static final long DELAY_FOR_ACTION_DOWN = 200;
    public static final long DELAY_FOR_ACTION_MOVE = 50;
    public long delayForAction = DELAY_FOR_ACTION_DOWN;
    private GameFieldParams gameFieldParams;
    private GameProcess gameProcess;
    private boolean isRunning;
    private Handler handler = new Handler();
    private SoundPlayer soundPlayer;
    private GameFieldActivity activity;
    public TetrisControlListener(GameFieldActivity activity, GameFieldParams gameFieldParams, GameProcess gameProcess, SoundPlayer soundPlayer){
        this.gameFieldParams = gameFieldParams;
        this.gameProcess = gameProcess;
        this.soundPlayer = soundPlayer;
        this.activity = activity;
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getAction() == MotionEvent.ACTION_UP){
            isRunning = false;
            return true;
        }
        boolean res = true;
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            if(isWithinTopScreen(motionEvent)){
                performTopScreenAction(motionEvent);
            } else {
                performGameScreenEvent(motionEvent);
            }
        }
        return res;
    }

    private boolean isWithinTopScreen(MotionEvent motionEvent) {
        return motionEvent.getY() < gameFieldParams.getGameScreenStartY();
    }

    private void performTopScreenAction(MotionEvent motionEvent) {
        if(isWithinBackButton(motionEvent.getX(), motionEvent.getY())) {
            activity.finish();
        }
    }

    private boolean isWithinBackButton(float x, float y) {
        int buttonLeft = gameFieldParams.getBackButtonLeft();
        int center = gameFieldParams.getBackButtonCenter();
        if(x< buttonLeft || x > buttonLeft + gameFieldParams.getBackButtonSize()){
            return false;
        }
        if(y < center - gameFieldParams.getBackButtonSize()/2){
            return false;
        }
        if(y > center + gameFieldParams.getBackButtonSize()/2){
            return false;
        }
        return true;
    }


    private void performGameScreenEvent(MotionEvent motionEvent) {
        delayForAction = DELAY_FOR_ACTION_DOWN;
        isRunning = true;
        soundPlayer.playActionOk();
        action(motionEvent);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    action(motionEvent);
                    handler.postDelayed(this, DELAY_FOR_ACTION_MOVE);
                }
            }
        }, DELAY_FOR_ACTION_DOWN);
    }


    private boolean action(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        boolean res = true;
        if (x < gameFieldParams.getMoveLeftButtonBorder()) {
            gameProcess.moveLeft();
        } else if (x > gameFieldParams.getMoveRightButtonBorder()) {
            gameProcess.moveRight();
        } else if (y < gameFieldParams.getRotateButtonBorder()) {
            gameProcess.rotateRight();
            res = false;
            isRunning = false;
        } else if (y > gameFieldParams.getMoveDownButtonBorder()) {
            gameProcess.moveDown();
        }
        return res;
    }
}
