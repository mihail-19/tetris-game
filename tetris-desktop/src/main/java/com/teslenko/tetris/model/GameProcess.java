package com.teslenko.tetris.model;

/**
 * Tetris game process. start() and stop() methods are abstract
 * because game running process depends on UI solution.
 * 
 * @author Mykhailo Teslenko
 *
 */
public abstract class GameProcess {
	private GameField gameField;
	protected TetrisController tetrisController;
	private FigureRandomFactory figureFactory;
	private int score;
	public GameProcess(GameField gameField, TetrisController tetrisController, FigureRandomFactory figureFactory) {
		this.gameField = gameField;
		this.tetrisController = tetrisController;
		this.figureFactory = figureFactory;
	}
	
	/**
	 * Single step of the game, that should automatically run in circle until game end.
	 * 
	 * First, if game figure is null, set new random game figure.
	 * 
	 * Second, move that figure down if it is possible. Add figure dots to surface dots of game field otherwise.
	 * 
	 * Third, confirm that game is not finished. If it is finished, stop game.
	 * 
	 * 
	 */
	public void startGameStep() {
		setGameFigureIfNull();
		if (gameField.couldMoveDown()) {
			gameField.moveDown();
		} else {
			gameField.addCurrentFigureToSurface();
			score += gameField.eraseFilledLines();
			tetrisController.paintScore(score);
			changeGameSpeed();
		}
		tetrisController.paintAllDots(gameField.getSurfaceFigures(), gameField.getFigure());
		tryFinishGame();
	}
	
	
	/**
	 * Move figure left.
	 */
	public void moveLeft() {
		if (gameField.couldMoveLeft()) {
			gameField.moveLeft();
			tetrisController.paintAllDots(gameField.getSurfaceFigures(), gameField.getFigure());
		}
	}

	/**
	 * Move figure right.
	 */
	public void moveRight() {
		if (gameField.couldMoveRight()) {
			gameField.moveRight();
			tetrisController.paintAllDots(gameField.getSurfaceFigures(), gameField.getFigure());
		}
	}
	
	/**
	 * Move figure down.
	 */
	public void moveDown() {
		if (gameField.couldMoveDown()) {
			gameField.moveDown();
			tetrisController.paintAllDots(gameField.getSurfaceFigures(), gameField.getFigure());
		}
	}
	
	/**
	 * ROtate figure.
	 */
	public void rotateRight() {
		if(gameField.getFigure() != null) {
			gameField.rotate();
			tetrisController.paintAllDots(gameField.getSurfaceFigures(), gameField.getFigure());
		}
	}
	
	/**
	 * Game score.
	 * @return
	 */
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	
	/**
	 * Starts game process
	 */
	public abstract void start();

	/**
	 * Stops game process
	 */
	public abstract void stop();
	
	/**
	 * Change of game speed
	 */
	public abstract void changeGameSpeed();
	
	private void setGameFigureIfNull() {
		if (gameField.getFigure() == null) {
			gameField.setFigure(figureFactory.getFigure());
			tetrisController.paintAllDots(gameField.getSurfaceFigures(), gameField.getFigure());
		}
	}
	
	private void tryFinishGame() {
		gameField.tryFinish();
		if (gameField.isFinished()) {
			tetrisController.paintGameOver();
			stop();
		}
	}
}
