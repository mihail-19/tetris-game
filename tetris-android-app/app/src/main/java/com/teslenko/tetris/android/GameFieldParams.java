package com.teslenko.tetris.android;

public class GameFieldParams {
    public static final int DEFAULT_DOT_SIZE = 90;
    public static final int DEFAULT_FIELD_HEIGHT = 20;
    private int dotSize = DEFAULT_DOT_SIZE;

    private int screenWidth;
    private int screenHeight;
    private int fieldWidth;
    private int fieldHeight;
    private int gameScreenStartY;
    private int backButtonLeft = 40;
    private int backButtonSize = 120;
    private int backButtonCenter;
    public GameFieldParams(int screenWidth, int screenHeight){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        calculateFieldSize();
    }

    public void calculateFieldSize(){
        fieldWidth = screenWidth/dotSize;
        fieldHeight = DEFAULT_FIELD_HEIGHT;
        int heightInPix = fieldHeight*dotSize;
        gameScreenStartY = screenHeight - heightInPix;
        backButtonCenter = gameScreenStartY/2;
    }


    public int getDotSize() {
        return dotSize;
    }

    public void setDotSize(int dotSize) {
        this.dotSize = dotSize;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public void setFieldWidth(int fieldWidth) {
        this.fieldWidth = fieldWidth;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public void setFieldHeight(int fieldHeight) {
        this.fieldHeight = fieldHeight;
    }

    public int getMoveLeftButtonBorder(){
       return screenWidth / 4;
    }
    public int getMoveRightButtonBorder(){
        return screenWidth - screenWidth / 4;
    }
    public int getMoveDownButtonBorder(){
       return screenHeight - screenHeight / 4;
    }
    public int getRotateButtonBorder(){
        return getMoveDownButtonBorder() - 200;
    }

    public int getBackButtonLeft() {
        return backButtonLeft;
    }

    public void setBackButtonLeft(int backButtonLeft) {
        this.backButtonLeft = backButtonLeft;
    }

    public int getBackButtonSize() {
        return backButtonSize;
    }

    public void setBackButtonSize(int backButtonRigh) {
        this.backButtonSize = backButtonSize;
    }

    public int getGameScreenStartY() {
        return gameScreenStartY;
    }

    public void setGameScreenStartY(int gameScreenStartY) {
        this.gameScreenStartY = gameScreenStartY;
    }

    public int getBackButtonCenter() {
        return backButtonCenter;
    }
}
