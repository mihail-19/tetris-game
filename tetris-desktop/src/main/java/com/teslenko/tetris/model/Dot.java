package com.teslenko.tetris.model;


/**
 * Base element of game field, all have similar size.
 * Contains position - X and Y coordinates. Coordinates system:
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
 * Has methods to move dot.
 * 
 * Class does not contain restriction so coordinates could be negative.
 * @author Mykhailo Teslenko
 *
 */
public class Dot{
	private int x;
	private int y;
	public Dot(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Dot() {
		
	}
	
	/**
	 * Move left - decrease X position for 1 point
	 */
	public void moveLeft() {
		x--;
	}
	
	/**
	 * Move right - increase X position for 1 point
	 */
	public void moveRight() {
		x++;
	}
	/**
	 * Moves dot down - increase Y position for 1 point
	 */
	public void moveDown() {
		y++;
	}
	
	/**
	 * Move up - decrease Y position for 1 point
	 * 
	 */
	public void moveUp() {
		y--;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dot other = (Dot) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Dot [x=" + x + ", y=" + y + "]";
	}
	
}
