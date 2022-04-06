package com.teslenko.tetris.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Figure with one single dot, containing in 0 element of dots list.
 * 
 * Rotate method have no effect to dot.
 * 
 * @author Mykhailo Tesloenko
 *
 */
public class DotFigure extends Figure{
	private List<Dot> dots;
	
	/**
	 * Creates dot at zero position
	 */
	public DotFigure(String color) {
		this(color, 0);
	}
	
	/**
	 * Creates dot at given X position
	 * @param initX
	 */
	public DotFigure(String color, int initX) {
		super(color);
		dots = new ArrayList<>();
		dots.add(new Dot(initX, 0));
	}
	@Override
	public List<Dot> getDots() {
		return dots;
	}
	@Override
	public void rotate() {
		
	}
	@Override
	public List<Dot> getDotsAfterRotation() {
		return dots;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dots == null) ? 0 : dots.hashCode());
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
		DotFigure other = (DotFigure) obj;
		if (dots == null) {
			if (other.dots != null)
				return false;
		} else if (!dots.equals(other.dots))
			return false;
		return true;
	}
	
}
