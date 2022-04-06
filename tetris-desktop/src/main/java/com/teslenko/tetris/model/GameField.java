package com.teslenko.tetris.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Core Tetris class - game field and all elements on it.
 * Contains methods of interaction with that elements.
 * 
 * All elements are pure model that could be used in any UI.
 *  
 *  Coordinate system is: 
 *  
 * 		    	X	 
 *       0 - 1 - 2 - n
 *		 |
 *		 1
 *	   Y |
 *		 2
 *		 |
 *		 n
 * 
 * X and Y restricted inclusively by zero from left and top, and by width and 
 * height params exclusively from right and bottom. Any {@link Dot}
 * could have X from 0 to dotsInRow-1 value, Y - from 0 to rowsNum-1 value.
 * 
 * @author Mykhailo Teslenko
 *
 */
public class GameField {
	private boolean isFinished;
	private int rowsNum;
	private int dotsInRow;
	private Figure figure;
	private List<Figure> surfaceFigures;
	
	/**
	 * Constructs a game field with given rows number and rows length
	 * - dots number, vertical and horizontal.
	 * @param rowsNum
	 * @param dotsInRow
	 */
	public GameField(int rowsNum, int dotsInRow) {
		this.rowsNum = rowsNum;
		this.dotsInRow = dotsInRow;
		resetGameField();
	}
	
	/**
	 * Resets game field, removing all dots.
	 */
	public void resetGameField() {
		surfaceFigures = new ArrayList<>();
	}
	
	/**
	 * Indicates whether figure in this field could move down.
	 * @return
	 */
	public boolean couldMoveDown() {
		if(figure == null) {
			return false;
		}
		for(Dot dot : figure.getDots()) {
			int y = dot.getY();
			int x = dot.getX();
			if(y >= rowsNum - 1) {
				return false;
			}
			for(Dot surfaceDot : getSurfaceDots()) {
				if(x == surfaceDot.getX() && y == surfaceDot.getY() - 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Indicates whether figure could move right in this field. 
	 * @return
	 */
	public boolean couldMoveRight() {
		if(figure == null) {
			return false;
		}
		for(Dot dot : figure.getDots()) {
			if(dot.getX() >= dotsInRow - 1) {
				return false;
			}
			int y = dot.getY();
			int x = dot.getX();
			for(Dot surfaceDot : getSurfaceDots()) {
				if(y == surfaceDot.getY() && x == surfaceDot.getX() - 1) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Indicates whether figure could move left in this field. 
	 * @return
	 */
	public boolean couldMoveLeft() {
		if(figure == null) {
			return false;
		}
		for(Dot dot : figure.getDots()) {
			if(dot.getX() <= 0) {
				return false;
			}
			int y = dot.getY();
			int x = dot.getX();
			for(Dot surfaceDot : getSurfaceDots()) {
				if(y == surfaceDot.getY() && x == surfaceDot.getX() + 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Moves figure down if could.
	 */
	public void moveDown() {
		if(couldMoveDown()) {
			figure.moveDown();
		}
	}

	/**
	 * Moves figure left if could.
	 */
	public void moveLeft() {
		if(couldMoveLeft()) {
			figure.moveLeft();
		}
	}
	
	/**
	 * Move figure right if could.
	 */
	public void moveRight() {
		if(couldMoveRight()) {
			figure.moveRight();
		}
	}
	
	/**
	 * Rotate a figure. Rotation is impossible if
	 * figure could not move down.
	 * 
	 * The order of rotation is not defined and depends on 
	 * actual {@Figure} realization.
	 * 
	 * If after rotation figure dots are out of game field borders,
	 * it will be moved in opposite direction.
	 * 
	 */
	public void rotate() {
		if(!couldRotate()) {
			return;
		}
		figure.rotate();
		returnToFieldFromRightBorder(figure.getDots());
		returnToFieldFromLeftBorder(figure.getDots());
	}
	public boolean couldRotate() {
		if(figure == null || !couldMoveDown()) {
			return false;
		}
		List<Dot> dotsAfterRotation = figure.getDotsAfterRotation();
		returnToFieldFromRightBorder(dotsAfterRotation);
		returnToFieldFromLeftBorder(dotsAfterRotation);
		for(Dot dot: dotsAfterRotation) {
			int x = dot.getX();
			int y = dot.getY();
			if(y < 0 || y >= rowsNum) {
				return false;
			}
			for(Dot surfaceDot : getSurfaceDots()) {
				if(x == surfaceDot.getX() && y == surfaceDot.getY()) {
					return false;
				}
			}
		}
		return true;
	}
	
	private void returnToFieldFromRightBorder(List<Dot> dots) {
		while(dots.stream().filter(d -> d.getX() >= dotsInRow).count() > 0) {
			dots.forEach(d -> d.moveLeft());
		}
	}
	private void returnToFieldFromLeftBorder(List<Dot> dots) {
		while(dots.stream().filter(d -> d.getX() < 0).count() > 0) {
			dots.forEach(d -> d.moveRight());
		}
	}
	
	/**
	 * Trying to finish game. Checks all dots, if 
	 * one of them is on the top border of the field,
	 * game marked as finished.
	 */
	public void tryFinish() {
		for (Dot d : getSurfaceDots()) {
			if (d.getY() <= 1) {
				isFinished = true;
				break;
			}
		}
	}
	
	/**
	 * Erase all lines where dots fills the line completely.
	 * @return
	 */
	public int eraseFilledLines() {
		int dotsErased = 0;
		for(int i = 0; i<rowsNum; i++) {
			int dotsInLine= countDotsInLine(i);
			if(dotsInLine == dotsInRow) {
				eraseLine(i);
				dotsErased += dotsInRow;
			}
		}
		return dotsErased;
	}
	private void eraseLine(int line) {
		for(Figure figure : surfaceFigures) {
			figure.removeDotWithY(line);
		}
		surfaceFigures.removeIf(f -> f.getDots().size() == 0);
		getSurfaceDots().forEach(d -> {
			if(d.getY() < line) {
				d.moveDown();
			}
		});
	}
	private int countDotsInLine(int line) {
		return (int) getSurfaceDots().stream().filter(d -> d.getY() == line).count();
	}
	
	
	/**
	 * Returns game field surface (not figure) dots.
	 * @return
	 */
	public List<Dot> getSurfaceDots() {
		return surfaceFigures.stream().flatMap(f -> f.getDots().stream()).collect(Collectors.toList());
	}
	
	/**
	 * All figures already
	 * @return
	 */
	public List<Figure> getSurfaceFigures(){
		return surfaceFigures;
	}

	/**
	 * Returns current figure dots.
	 * @return
	 */
	public List<Dot> getFigureDots() {
		if (figure == null) {
			return new ArrayList<>();
		}
		return figure.getDots();
	}
	
	public boolean isFinished() {
		return isFinished;
	}
	
	/**
	 * Current game figure
	 * @return
	 */
	public Figure getFigure() {
		return figure;
	}
	
	public void addCurrentFigureToSurface() {
		surfaceFigures.add(figure);
		figure = null;
	}

	/**
	 * Sets a new figure into game field. Figure could be null.
	 * 
	 * Figure could not contain {@link Dot} with coordinates outside of the game field.
	 * 
	 * @param figure
	 */
	public void setFigure(Figure figure) {
		validateFigure(figure);
		this.figure = figure;
	}

	private void validateFigure(Figure figure) {
		if(figure == null) {
			return;
		}
		List<Dot> figureDots = figure.getDots();
		for(Dot dot : figureDots) {
			if(dot.getX() < 0 || dot.getX() > dotsInRow - 1) {
				throw new IllegalArgumentException("invalid figure, X is not inside game field " + figure);
			}
			if(dot.getY() < 0 || dot.getY() > rowsNum - 1) {
				throw new IllegalArgumentException("invalid figure, Y is not inside game field " + figure);
			}
		}
	}

	public int getRowsNum() {
		return rowsNum;
	}

	public void setRowsNum(int rowsNum) {
		this.rowsNum = rowsNum;
	}

	public int getDotsInRow() {
		return dotsInRow;
	}

	public void setDotsInRow(int dotsInRow) {
		this.dotsInRow = dotsInRow;
	}
	
	
	
}
