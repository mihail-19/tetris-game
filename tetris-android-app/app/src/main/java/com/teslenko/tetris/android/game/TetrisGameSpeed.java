package com.teslenko.tetris.android.game;

public enum TetrisGameSpeed {
    FIRST(4, 600), SECOND(10, 500),
    THIRD(20, 400), FOURTH(40, 300),
    FIFTH(60, 200);
    private int rowsToNext;
    private long tickDelay;
    private  TetrisGameSpeed(int rowsToNext, long tickDelay){
        this.rowsToNext = rowsToNext;
        this.tickDelay = tickDelay;
    }
    public int getRowsToNext(){
        return rowsToNext;
    }
    public long getTickDelay(){
        return tickDelay;
    }
}
