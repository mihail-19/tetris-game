package com.teslenko.tetris.model;

import java.util.List;

/**
 * Tetris figure. Contains common methods for all figures.
 * Derived classes should contain real geometry of a figure.
 * 
 * Figure is a list of {@link Dot} with given geometry.
 * Geometry of dots should be changed only inside {@Figure} class.
 * 
 * @author Mykhailo Teslenko
 *
 */
public abstract class Figure {
	private String color;
	public Figure(String color) {
		 this.color = color;
	}
	
	/**
	 * Moves figure down without restrictions
	 */
	public void moveDown() {
		getDots().forEach((d) -> d.moveDown());
	}
	
	/**
	 * Moves figure left without restrictions
	 */
	public void moveLeft() {
		getDots().forEach((d) -> d.moveLeft());
	}
	
	/**
	 * Moves figure right without restrictions
	 */
	public void moveRight() {
		getDots().forEach((d) -> d.moveRight());
	}
	
	/**
	 * Removes given dot
	 */
	public void removeDot(Dot dot) {
		getDots().remove(dot);
	}
	
	/**
	 * Removes dots with given x (e.g. when erasing a line)
	 * @param x
	 */
	public void removeDotWithY(int y) {
		getDots().removeIf(d -> d.getY() == y);
	}
	
	/**
	 * Rotate figure
	 */
	public abstract void rotate();
	
	/**
	 * Return dots in position they would be after rotation.
	 * Should be used to confirm the rotation is possible.
	 * @return
	 */
	public abstract List<Dot> getDotsAfterRotation();
	
	/**
	 * Get all figure dots
	 * @return
	 */
	public abstract List<Dot> getDots();
	
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return getDots().toString();
	}
	
}
