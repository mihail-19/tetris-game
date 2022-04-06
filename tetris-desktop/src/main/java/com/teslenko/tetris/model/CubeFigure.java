package com.teslenko.tetris.model;

import java.util.ArrayList;
import java.util.List;

public class CubeFigure extends Figure{
	private List<Dot> dots;
	public CubeFigure(String color) {
		this(color, 0);
	}
	public CubeFigure(String color, int initX) {
		super(color);
		dots = new ArrayList<>();
		dots.add(new Dot(initX, 0));
		dots.add(new Dot(initX+1, 0));
		dots.add(new Dot(initX, 1));
		dots.add(new Dot(initX+1, 1));
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
		CubeFigure other = (CubeFigure) obj;
		if (dots == null) {
			if (other.dots != null)
				return false;
		} else if (!dots.equals(other.dots))
			return false;
		return true;
	}
	
}
