package com.teslenko.tetris.android.graphic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.teslenko.tetris.android.GameFieldParams;
import com.teslenko.tetris.android.game.TetrisGameSpeed;

public class TextOnTop {
    private int textY;
    private Paint textPaint;
    private Paint backButtonPaint;
    private Paint backArrowPaint;
    private GameFieldParams gameFieldParams;
    public static final int TEXT_SIZE = 52;
    public TextOnTop(int fieldTopY, GameFieldParams gameFieldParams) {
        this.textY = fieldTopY/2;
        this.gameFieldParams = gameFieldParams;
        textPaint = new Paint();
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setColor(Color.DKGRAY);
        backButtonPaint = new Paint();
        backButtonPaint.setColor(Color.parseColor("#FF6200EE"));
        backButtonPaint.setStyle(Paint.Style.STROKE);
        backArrowPaint = new Paint();
        backArrowPaint.setColor(Color.parseColor("#FF6200EE"));
    }
    public void paintScore(Canvas canvas, int score){
        int left = gameFieldParams.getBackButtonLeft() + gameFieldParams.getBackButtonSize() + 70;
        canvas.drawText("Score: " + score,  left,
                textY + textPaint.getTextSize()/2, textPaint);
    }

    public void paintGameSpeed(Canvas canvas, int canvasWidth, TetrisGameSpeed tetrisGameSpeed) {
        String text = "x" + (tetrisGameSpeed.ordinal() + 1);
        int x = canvasWidth - 70 - text.length()*(int)textPaint.getTextSize();
        canvas.drawText(text, x, textY + textPaint.getTextSize()/2, textPaint);
    }

    public void paintGameOver(Canvas canvas, int canvasWidth) {
        String text = "Game over!";
        int centerX = canvasWidth/2 - text.length();
        canvas.drawText(text, centerX, textY + textPaint.getTextSize()/2 , textPaint);
    }



    public void paintBackButton(Canvas canvas){
        int center = gameFieldParams.getBackButtonCenter();
        int top = center - gameFieldParams.getBackButtonSize()/2;
        int right = gameFieldParams.getBackButtonLeft() + gameFieldParams.getBackButtonSize();
        int bottom = center + gameFieldParams.getBackButtonSize()/2;
        canvas.drawRect(gameFieldParams.getBackButtonLeft(), top,
                right, bottom, backButtonPaint);
        int padding = 20;
        Point l = new Point(gameFieldParams.getBackButtonLeft() + padding, center);
        int arrowRightX = gameFieldParams.getBackButtonLeft() + gameFieldParams.getBackButtonSize() - padding*2;
        Point rt = new Point(arrowRightX, center - gameFieldParams.getBackButtonSize()/2 + padding);
        Point rb = new Point(arrowRightX, center + gameFieldParams.getBackButtonSize()/2 - padding);
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.lineTo(rt.x, rt.y);
        path.lineTo(rb.x, rb.y);
        path.lineTo(l.x, l.y);
        path.lineTo(rt.x, rt.y);
        canvas.drawPath(path, backArrowPaint);
    }
}
