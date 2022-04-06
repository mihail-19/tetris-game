package com.teslenko.tetris.model;


/**
 * Random {@link Figure} generator.
 * @author Mykhailo Teslenko
 *
 */
public class FigureRandomFactory {
	private int maxX;
	/**
	 * Creates factory according to max dots in row ({@link GameField} width).
	 * This prevents creation figure with {@link Dot} out of game field borders. 
	 * @param columns
	 */
	public FigureRandomFactory(int dotsInRow) {
		maxX = dotsInRow-4;
	}
	public Figure getFigure() {
		int rnd = getRndInt(0, 5);
		if(rnd == 0) {
			return new DotFigure(getColor(), getRndInt(0, maxX));
		} else if(rnd == 1) {
			return new CubeFigure(getColor(), getRndInt(0, maxX));
		} else if(rnd == 2){
			return new LineFigure(getColor(), getRndInt(2, 5), getRndBoole(), getRndInt(0, maxX));
		} else if(rnd == 3) {
			return new LFigureLeft(getColor(), LFigurePositions.values()[getRndInt(0, 4)]);
		} else {
			return new LFigureRight(getColor(), LFigurePositions.values()[getRndInt(0, 4)]);
		}
	}
	
	private int getRndInt(int min, int max) {
		double rnd = Math.random();
		int d = max-min;
		return (int) (min + d*rnd);
	}
	private boolean getRndBoole() {
		return Math.random() <= 0.5;
	}
	
	/**
	 * Returns color. Could be changed in derived classes.
	 * @return
	 */
	protected String getColor() {
		int rnd = getRndInt(0, 5);
		String color;
		switch (rnd) {
		case 0:
			color = "#990099";
			break;
		case 1:
			color = "#ff6600";
			break;
		case 2: 
			color = "#ff9933";
			break;
		case 3:
			color = "#cc9900";
			break;
		case 4:
			color = "#669900";
			break;
		default:
			color = "#006600";
			break;
		}
		return color;
	}
}
