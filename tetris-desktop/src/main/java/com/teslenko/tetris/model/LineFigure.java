package com.teslenko.tetris.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Line Figure - two or more dots all in one line, horizontal or vertical
 * @author Mykhailo Teslenko
 *
 */
public class LineFigure extends Figure{
	public static final int MIN_LENGTH = 2;
	private List<Dot> dots;
	private int length;
	private boolean isHorizontal;
	public static final int INIT_Y_DEFAULT = 0;
	public static final int INIT_X_DEFAULT = 0;
	/**
	 * Creates new Line figure with given length and X position 0.
	 * @param length
	 * @param isHorizontal
	 */
	public LineFigure(String color, int length, boolean isHorizontal) {
		this(color, length, isHorizontal, INIT_X_DEFAULT);
	}
	
	public LineFigure(String color, int length, boolean isHorizontal, int initX) {
		this(color, length, isHorizontal, initX, INIT_Y_DEFAULT);
	}
	public LineFigure(String color, int length, boolean isHorizontal, int initX, int initY) {
		super(color);
		validateLength(length);
		this.length = length;
		this.isHorizontal = isHorizontal;
		buildLine(initX, initY);
	}
	
	
	@Override
	public void rotate() {
		dots = getDotsAfterRotation();
		isHorizontal = !isHorizontal;
	}
	
	@Override
	public List<Dot> getDotsAfterRotation() {
		List<Dot> dotsAfterRotation = new ArrayList<>();
		int initX = dots.get(0).getX();
		int initY = dots.get(0).getY();
		if(isHorizontal) {
			buildVertical(initX, initY, dotsAfterRotation);
		} else {
			buildHodizontal(initX, initY, dotsAfterRotation);
		}
		return dotsAfterRotation;
	}
	@Override
	public List<Dot> getDots() {
		return dots;
	}
	
	
	
	private void validateLength(int length) {
		if(length < MIN_LENGTH) {
			throw new IllegalArgumentException("line should have length at least " + MIN_LENGTH);
		}		
	}
	
	private void buildLine(int initX, int initY) {
		dots = new ArrayList<>();
		if(isHorizontal) {
			buildHodizontal(initX, initY, dots);
		} else {
			buildVertical(initX, initY, dots);
		}
	}
	
	private void buildHodizontal(int initX, int initY, List<Dot> dots) {
		for(int i = 0; i< length; i++) {
			dots.add(new Dot(initX + i, initY));
		}
	}
	
	private void buildVertical(int initX, int initY, List<Dot> dots) {
		for(int i = 0; i< length; i++) {
			dots.add(new Dot(initX, initY + i));
		}
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dots == null) ? 0 : dots.hashCode());
		result = prime * result + (isHorizontal ? 1231 : 1237);
		result = prime * result + length;
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
		LineFigure other = (LineFigure) obj;
		if (dots == null) {
			if (other.dots != null)
				return false;
		} else if (!dots.equals(other.dots))
			return false;
		if (isHorizontal != other.isHorizontal)
			return false;
		if (length != other.length)
			return false;
		return true;
	}

}
