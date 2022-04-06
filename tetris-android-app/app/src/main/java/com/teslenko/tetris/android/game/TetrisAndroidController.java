package com.teslenko.tetris.android.game;

import com.teslenko.tetris.android.graphic.MyCanvas;
import com.teslenko.tetris.android.SoundPlayer;
import com.teslenko.tetris.model.Figure;
import com.teslenko.tetris.model.TetrisController;

import java.util.List;

public class TetrisAndroidController implements TetrisController {
    private MyCanvas myCanvas;
    private SoundPlayer soundPlayer;
    public TetrisAndroidController(MyCanvas myCanvas, SoundPlayer soundPlayer){
        this.myCanvas = myCanvas;
        this.soundPlayer = soundPlayer;
    }

    @Override
    public void paintAllDots(List<Figure> surfaceFigures, Figure figure) {
        myCanvas.paintDots(surfaceFigures, figure);
        myCanvas.invalidate();
    }

    @Override
    public void paintScore(int score) {
        if(myCanvas.getScore() != score){
            soundPlayer.playLineErased();
        }
        myCanvas.setScore(score);
        myCanvas.invalidate();
    }

    @Override
    public void paintGameOver() {
        soundPlayer.playeGameOver();
        myCanvas.setIsFinished(true);
        myCanvas.invalidate();
    }


}
