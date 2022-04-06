package com.teslenko.tetris.model;

import java.util.List;

import com.teslenko.tetris.fx.GameSpeed;

/**
 * Interface for communication from model to view.
 * Updates view after model changes.
 * @author Mykhailo Teslenko
 *
 */
public interface TetrisController {
	/**
	 * Paint all dots of the game - for the current figure and
	 * for game field dots.
	 * @param surfaceDots
	 * @param figureDots
	 */
	void paintAllDots(List<Figure> surfaceFigures, Figure figureDots);
	
	/**
	 * Paint current score value
	 * @param score
	 */
	void paintScore(int score);
	
	/**
	 * Paint game over message
	 */
	void paintGameOver();
	
}
