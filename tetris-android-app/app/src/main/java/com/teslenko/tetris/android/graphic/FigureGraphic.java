package com.teslenko.tetris.android.graphic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.teslenko.tetris.android.GameFieldParams;
import com.teslenko.tetris.model.Dot;
import com.teslenko.tetris.model.Figure;

public class FigureGraphic {
    private Paint paint;
    private GameFieldParams gameFieldParams;
    private int gameFieldStartY;

    public FigureGraphic(GameFieldParams gameFieldParams, int gameFieldStartY) {
        this.gameFieldParams = gameFieldParams;
        this.gameFieldStartY = gameFieldStartY;
        paint = new Paint();
        paint.setColor(Color.parseColor("#FF6200EE"));
    }

    public void paintFigure(Canvas canvas, Figure figure){
        if(figure == null){
            return;
        }
        try {
            int color = Color.parseColor(figure.getColor());
            paint.setColor(color);
        } catch (Exception e){
            Log.e("tetriserror", e.toString());
        }
        for(Dot dot : figure.getDots()){
            paintDot(canvas, dot, paint);
        }
    }
    public void paintDot(Canvas canvas, Dot dot, Paint paint){
        if(dot == null){
            return;
        }
        int dotSize = gameFieldParams.getDotSize();
        try {
            canvas.drawRect(dot.getX() * dotSize,
                    dot.getY() * dotSize + gameFieldStartY,
                    dot.getX() * dotSize + dotSize,
                    dot.getY() * dotSize + dotSize + gameFieldStartY,
                    paint);
        } catch (Exception e){
            Log.e("tetriserror", e.toString());
        }
    }
}
