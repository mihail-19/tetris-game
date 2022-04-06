package com.teslenko.tetris.fx;

import javafx.util.Duration;

/**
 * Game speed constants.
 * @author Mykhailo Teslenko
 *
 */
public enum GameSpeed {
	
	FIRST(Duration.seconds(0.7), 4), SECOND(Duration.seconds(0.6), 8), THIRD(Duration.seconds(0.5), 20), 
	FOURTH(Duration.seconds(0.4), 35), FIFTH(Duration.seconds(0.3), 60), SIXS(Duration.seconds(0.2), 60);
	
	
	private Duration duration;
	private int coeffToChage;

	GameSpeed(Duration duration, int coeffToChange) {
		this.duration = duration;
		this.coeffToChage = coeffToChange;
	}
	
	
	/**
	 * Duration of tick for this speed
	 */
	public int getCoeffToChange() {
		return coeffToChage;
	}
	
	
	/**
	 * Number of rows to be eliminated to change speed to next
	 */
	public Duration getDuration() {
		return duration;
	}
	
}
