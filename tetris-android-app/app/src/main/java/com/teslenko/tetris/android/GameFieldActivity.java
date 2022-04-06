package com.teslenko.tetris.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import com.teslenko.tetris.android.game.GameRestoration;
import com.teslenko.tetris.android.game.TetrisAndroidController;
import com.teslenko.tetris.android.game.TetrisAndroidGameProcess;
import com.teslenko.tetris.android.graphic.MyCanvas;
import com.teslenko.tetris.model.FigureRandomFactory;
import com.teslenko.tetris.model.GameField;
import com.teslenko.tetris.model.TetrisController;

public class GameFieldActivity extends AppCompatActivity {
    private boolean isSoundOn = true;
    private boolean isVibrationOn = true;
    public static GameRestoration gameRestoration;
    private TetrisAndroidGameProcess gameProcess;
    MyCanvas myCanvas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameFieldParams gameFieldParams = setupGameFieldParams();
        SoundPlayer soundPlayer = SoundPlayer.getInstance(this);
        Log.i("GameFieldActivity", "game field width = " + gameFieldParams.getFieldWidth() + ", height = " + gameFieldParams.getFieldHeight());
        myCanvas = new MyCanvas(this, gameFieldParams);
        TetrisController tetrisController = new TetrisAndroidController(myCanvas, soundPlayer);
        if(gameRestoration == null || gameRestoration.getGameField().isFinished()){
            gameRestoration = new GameRestoration();
            gameRestoration.setGameField(
                    new  GameField(gameFieldParams.getFieldHeight(), gameFieldParams.getFieldWidth()));
        }
        gameProcess = new TetrisAndroidGameProcess(myCanvas,
                gameRestoration,
                tetrisController,
                new FigureRandomFactory(gameFieldParams.getFieldWidth()),
                soundPlayer);
        myCanvas.setOnTouchListener(new TetrisControlListener(this, gameFieldParams, gameProcess, soundPlayer));
        setContentView(myCanvas);
        gameProcess.start();
    }

    private GameFieldParams setupGameFieldParams(){
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int dpWidth = (int) (displayMetrics.widthPixels );
        int dpHeight = (int) (displayMetrics.heightPixels );
        GameFieldParams gameFieldParams = new GameFieldParams(dpWidth, dpHeight);
        gameFieldParams.calculateFieldSize();
        return gameFieldParams;
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameProcess.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        gameProcess.stop();
    }
}