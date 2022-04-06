package com.teslenko.tetris.android.graphic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import com.teslenko.tetris.android.GameFieldParams;
import com.teslenko.tetris.android.game.TetrisGameSpeed;
import com.teslenko.tetris.model.Dot;
import com.teslenko.tetris.model.Figure;

import java.util.ArrayList;
import java.util.List;

public class MyCanvas extends View {
    private List<Figure> surfaceFigures = new ArrayList<>();
    private Figure figure;
    private GameFieldParams gameFieldParams;
    private TopLine topLine;
    private int score;
    private TetrisGameSpeed tetrisGameSpeed;
    private boolean isFinished;

    private ControlButtonsGraphic buttonPainter;
    private TextOnTop textOnTop;
    private FigureGraphic figureGraphic;

    public MyCanvas(Context context, GameFieldParams gameFieldParams){
        super(context);

        this.gameFieldParams = gameFieldParams;
        topLine = new TopLine();
        buttonPainter = new ControlButtonsGraphic(gameFieldParams, topLine.startY);
        textOnTop = new TextOnTop(topLine.startY, gameFieldParams);
        figureGraphic = new FigureGraphic(gameFieldParams, topLine.startY);

    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Log.i("MyCanvas", "onDraw()");
        textOnTop.paintScore(canvas, score);
        textOnTop.paintGameSpeed(canvas, getWidth(), tetrisGameSpeed);
        textOnTop.paintBackButton(canvas);
        if(isFinished){
            textOnTop.paintGameOver(canvas, getWidth());
        }

        for(Figure f : surfaceFigures){
            figureGraphic.paintFigure(canvas, f);
        }
        figureGraphic.paintFigure(canvas, figure);
        canvas.drawLine(topLine.startX, topLine.startY, topLine.stopX, topLine.stopY, topLine.paint);
        buttonPainter.paintButtons(this, canvas);

    }



    /**
     * Sets figures. Do not paint them immediately. Figures will be painted
     * after invalidate() call.
     * @param surfaceFigures
     * @param figure
     */
    public void paintDots(List<Figure> surfaceFigures,Figure figure){
        this.surfaceFigures = surfaceFigures;
        this.figure = figure;
    }


    public void setScore(int score){
        this.score = score;
    }
    public int getScore(){
        return score;
    }
    public void setIsFinished(boolean isFinished){
        this.isFinished = isFinished;
    }
    public GameFieldParams getGameFieldParams(){
        return gameFieldParams;
    }
    public void setTetrisGameSpeed(TetrisGameSpeed tetrisGameSpeed){
        this.tetrisGameSpeed = tetrisGameSpeed;
    }
    private class TopLine{
        private int startX;
        private int startY;
        private int stopX;
        private int stopY;
        private Paint paint;
        public TopLine(){
            int heightInPix = gameFieldParams.getFieldHeight()*gameFieldParams.getDotSize();
            startX = 0;
            stopX = gameFieldParams.getScreenWidth();
            startY = gameFieldParams.getGameScreenStartY();
            stopY = startY;
            paint = new Paint();
            paint.setColor(Color.RED);
        }
    }
}
