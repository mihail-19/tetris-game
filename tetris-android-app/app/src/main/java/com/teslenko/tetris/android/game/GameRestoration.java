package com.teslenko.tetris.android.game;

import com.teslenko.tetris.model.GameField;

/**
 * Contains all params required to restore given game.
 */
public class GameRestoration {
    private GameField gameField;
    private TetrisGameSpeed tetrisGameSpeed = TetrisGameSpeed.FIRST;
    private int score;

    public GameField getGameField() {
        return gameField;
    }

    public void setGameField(GameField gameField) {
        this.gameField = gameField;
    }

    public TetrisGameSpeed getTetrisGameSpeed() {
        return tetrisGameSpeed;
    }

    public void setTetrisGameSpeed(TetrisGameSpeed tetrisGameSpeed) {
        this.tetrisGameSpeed = tetrisGameSpeed;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
