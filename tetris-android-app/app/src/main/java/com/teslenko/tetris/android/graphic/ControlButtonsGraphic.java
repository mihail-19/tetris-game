package com.teslenko.tetris.android.graphic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.teslenko.tetris.android.GameFieldParams;

public class ControlButtonsGraphic {
    private GameFieldParams gameFieldParams;
    private int fieldStartY;
    private Paint buttonPaint;
    private long startTime = System.currentTimeMillis();
    public static final long TIME_TO_SHOW_MILLIS = 7000;
    private boolean upAlpha = true;
    public ControlButtonsGraphic(GameFieldParams gameFieldParams, int fieldStartY) {
        this.gameFieldParams = gameFieldParams;
        this.fieldStartY = fieldStartY;
        buttonPaint = new Paint();
        buttonPaint.setStyle(Paint.Style.FILL);
        buttonPaint.setColor(Color.parseColor("#8686ac"));
        buttonPaint.setAlpha(10);
    }

    public void paintButtons(MyCanvas myCanvas, Canvas canvas){
        if(System.currentTimeMillis() - startTime < TIME_TO_SHOW_MILLIS) {
            paintLeftButton(canvas);
            paintRightButton(canvas);
            paintDownButton(canvas);
            paintRotateButton(canvas);
            int alpha = buttonPaint.getAlpha();
            if(upAlpha){
                if(alpha < 90) {
                    buttonPaint.setAlpha(buttonPaint.getAlpha() + 1);
                }else {
                    upAlpha = false;
                }
            } else {
                if(alpha > 10) {
                    buttonPaint.setAlpha(buttonPaint.getAlpha() - 1);
                }else {
                    upAlpha = true;
                }
            }
            myCanvas.invalidate();
        } else {
            buttonPaint.setAlpha(50);
            paintLeftButton(canvas);
            paintRightButton(canvas);
            paintDownButton(canvas);
            paintRotateButton(canvas);
        }
    }
    public void paintLeftButton(Canvas canvas){
        int center = gameFieldParams.getMoveDownButtonBorder() - (gameFieldParams.getMoveDownButtonBorder() - fieldStartY)/2;
        Point rt = new Point(gameFieldParams.getMoveLeftButtonBorder(), center - (center - fieldStartY)/3);
        Point rb = new Point(gameFieldParams.getMoveLeftButtonBorder(), center + (center - fieldStartY)/3);
        Point l = new Point(gameFieldParams.getMoveLeftButtonBorder()/2, center);
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.lineTo(rt.x, rt.y);
        path.lineTo(rb.x, rb.y);
        path.lineTo(l.x, l.y);
        path.lineTo(rt.x, rt.y);
        path.close();
        canvas.drawPath(path, buttonPaint);
    }
    public void paintRightButton(Canvas canvas){
        int center = gameFieldParams.getMoveDownButtonBorder() - (gameFieldParams.getMoveDownButtonBorder() - fieldStartY)/2;
        Point lt = new Point(gameFieldParams.getMoveRightButtonBorder(), center - (center - fieldStartY)/3);
        Point lb = new Point(gameFieldParams.getMoveRightButtonBorder(), center + (center - fieldStartY)/3);
        int rightX = gameFieldParams.getScreenWidth() - (gameFieldParams.getScreenWidth() - gameFieldParams.getMoveRightButtonBorder())/2;
        Point r = new Point(rightX, center);
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.lineTo(lt.x, lt.y);
        path.lineTo(lb.x, lb.y);
        path.lineTo(r.x, r.y);
        path.lineTo(lt.x, lt.y);
        path.close();
        canvas.drawPath(path, buttonPaint);
    }
    public void paintDownButton(Canvas canvas){
        int center = gameFieldParams.getScreenWidth()/2;
        Point l = new Point(2*center/3, gameFieldParams.getMoveDownButtonBorder());
        Point r = new Point(center + center/3, gameFieldParams.getMoveDownButtonBorder());
        int bottom = gameFieldParams.getScreenHeight() -  (gameFieldParams.getScreenHeight() - gameFieldParams.getMoveDownButtonBorder())/2;
        Point b = new Point(center, bottom);
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.lineTo(l.x, l.y);
        path.lineTo(r.x, r.y);
        path.lineTo(b.x, b.y);
        path.lineTo(l.x, l.y);
        path.close();
        canvas.drawPath(path, buttonPaint);
    }
    public void paintRotateButton(Canvas canvas){
        int cx = gameFieldParams.getScreenWidth()/2;
        int cy = gameFieldParams.getMoveDownButtonBorder() - (gameFieldParams.getMoveDownButtonBorder() - fieldStartY)/2;
        canvas.drawCircle(cx, cy, 100, buttonPaint);
    }



}
