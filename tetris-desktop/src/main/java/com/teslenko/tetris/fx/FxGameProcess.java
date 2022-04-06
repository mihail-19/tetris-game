package com.teslenko.tetris.fx;

import com.teslenko.tetris.model.FigureRandomFactory;
import com.teslenko.tetris.model.GameField;
import com.teslenko.tetris.model.GameProcess;
import com.teslenko.tetris.model.TetrisController;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

public class FxGameProcess extends GameProcess {
	private Timeline timeline;
	private int dotsInRow;
	private GameSpeed gameSpeed;
	private MainWindow mainWindow;
	
	public FxGameProcess(MainWindow mainWindow, GameField gameField, TetrisController tetrisController, FigureRandomFactory figureFactory,
			GameSpeed gameSpeed) {
		super(gameField, tetrisController, figureFactory);
		this.gameSpeed = gameSpeed;
		this.mainWindow = mainWindow;
		dotsInRow = gameField.getDotsInRow();
	}

	@Override
	public void start() {
		mainWindow.paintGameSpeed(gameSpeed);
		KeyFrame gameTick = new KeyFrame(gameSpeed.getDuration(), event -> {
			startGameStep();
		});
		timeline = new Timeline(gameTick);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	/**
	 * Changes game speed according to current score and {@link GameSpeed}
	 * coeff to change
	 */
	@Override
	public void changeGameSpeed() {
		int score = getScore();
		if(gameSpeed == GameSpeed.SIXS) {
			return;
		}
		if (score >= dotsInRow * gameSpeed.getCoeffToChange()) {
			gameSpeed = GameSpeed.values()[gameSpeed.ordinal() + 1];
			resetTimer(gameSpeed);
			mainWindow.paintGameSpeed(gameSpeed);
		}
	}

	
	private void resetTimer(GameSpeed gameSpeed) {
		KeyFrame gameTick = new KeyFrame(gameSpeed.getDuration(), event -> {
			startGameStep();
		});
		timeline.stop();
		timeline.getKeyFrames().setAll(gameTick);
		timeline.play();
	}
	
	
	@Override
	public void stop() {
		timeline.stop();
	}

}
