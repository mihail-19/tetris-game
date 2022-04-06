package com.teslenko.tetris.android.game;

import android.os.Handler;
import android.util.Log;

import com.teslenko.tetris.android.SoundPlayer;
import com.teslenko.tetris.android.graphic.MyCanvas;
import com.teslenko.tetris.model.FigureRandomFactory;
import com.teslenko.tetris.model.GameField;
import com.teslenko.tetris.model.GameProcess;
import com.teslenko.tetris.model.TetrisController;

public class TetrisAndroidGameProcess extends GameProcess {
    private Handler handler = new Handler();
    private MyCanvas myCanvas;
    private boolean isStopped = true;
    private TetrisGameSpeed tetrisGameSpeed;
    private SoundPlayer soundPlayer;
    private final GameRestoration gameRestoration;
    public TetrisAndroidGameProcess(MyCanvas myCanvas, GameRestoration gameRestoration,
                                    TetrisController tetrisController, FigureRandomFactory figureFactory,
                                    SoundPlayer soundPlayer){
        super(gameRestoration.getGameField(), tetrisController, figureFactory);
        this.myCanvas = myCanvas;
        this.soundPlayer = soundPlayer;
        this.gameRestoration = gameRestoration;
        tetrisGameSpeed = gameRestoration.getTetrisGameSpeed();
        setScore(gameRestoration.getScore());
        myCanvas.setTetrisGameSpeed(tetrisGameSpeed);
        myCanvas.setScore(getScore());
        myCanvas.invalidate();
    }

    @Override
    public void startGameStep() {
        try {
            super.startGameStep();
        } catch (Exception e){
            Log.e("tetriserror gameprocess", e.toString());
        }
    }

    @Override
    public void start() {
        if(!isStopped){
            return;
        }
        isStopped = false;
        handler.postDelayed(new Runnable() {
            public void run() {
                if(!isStopped) {
                    startGameStep();
                    handler.postDelayed(this, tetrisGameSpeed.getTickDelay());
                }
            }
        }, tetrisGameSpeed.getTickDelay());
    }

    @Override
    public void stop() {
        isStopped = true;
    }

    @Override
    public void changeGameSpeed() {
        gameRestoration.setScore(getScore());
        TetrisGameSpeed [] values = TetrisGameSpeed.values();
        if(tetrisGameSpeed == values[values.length-1]){
            return;
        }
        if(getScore() >= tetrisGameSpeed.getRowsToNext()*myCanvas.getGameFieldParams().getFieldWidth()){
            soundPlayer.playNewLevel();
            tetrisGameSpeed = values[tetrisGameSpeed.ordinal() + 1];
            gameRestoration.setTetrisGameSpeed(tetrisGameSpeed);
            myCanvas.setTetrisGameSpeed(tetrisGameSpeed);
            myCanvas.invalidate();
        }
    }

    public void setMyCanvas(MyCanvas myCanvas){
        this.myCanvas = myCanvas;
    }

}
