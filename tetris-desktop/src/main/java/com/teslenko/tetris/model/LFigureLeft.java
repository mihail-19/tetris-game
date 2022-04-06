package com.teslenko.tetris.model;

import java.util.ArrayList;
import java.util.List;

public class LFigureLeft extends Figure {
	private List<Dot> dots;
	private LFigurePositions position;

	public LFigureLeft(String color, LFigurePositions position) {
		this(color, 0, position);
	}

	public LFigureLeft(String color, int initX, LFigurePositions position) {
		super(color);
		this.position = position;
		dots = new ArrayList<>();
		if (position == LFigurePositions.HOR_LEFT) {
			buildHorLeft(initX, 0, dots);
		} else if (position == LFigurePositions.VERT_HIGH) {
			buildVertHigh(initX, 0, dots);
		} else if (position == LFigurePositions.HOR_RIGHT) {
			buildHorRight(initX, 0, dots);
		} else if (position == LFigurePositions.VERT_LOW) {
			buildVertLow(initX, 0, dots);
		}
	}

	private void buildVertLow(int initX, int initY, List<Dot> dots) {
		dots.add(new Dot(initX, initY));		
		dots.add(new Dot(initX, initY+1));		
		dots.add(new Dot(initX, initY+2));		
		dots.add(new Dot(initX+1, initY+2));		
	}

	private void buildHorRight(int initX, int initY, List<Dot> dots) {
		dots.add(new Dot(initX, initY+1));		
		dots.add(new Dot(initX+1, initY+1));		
		dots.add(new Dot(initX+2, initY+1));		
		dots.add(new Dot(initX+2, initY));		
	}

	private void buildVertHigh(int initX, int initY, List<Dot> dots) {
		dots.add(new Dot(initX, initY));
		dots.add(new Dot(initX+1, initY));
		dots.add(new Dot(initX+1, initY+1));
		dots.add(new Dot(initX+1, initY+2));
		
	}

	private void buildHorLeft(int initX, int initY, List<Dot> dots) {
		dots.add(new Dot(initX, initY));
		dots.add(new Dot(initX, initY + 1));
		dots.add(new Dot(initX + 1, initY));
		dots.add(new Dot(initX + 2, initY));
	}

	@Override
	public void rotate() {
		dots = getDotsAfterRotation();
		if(position == LFigurePositions.HOR_LEFT) {
			position = LFigurePositions.VERT_HIGH;
		} else if(position == LFigurePositions.VERT_HIGH) {
			position = LFigurePositions.HOR_RIGHT;
		} else if(position == LFigurePositions.HOR_RIGHT) {
			position = LFigurePositions.VERT_LOW;
		} else if(LFigurePositions.VERT_LOW == position) {
			position = LFigurePositions.HOR_LEFT;
		}
	}
	
	@Override
	public List<Dot> getDotsAfterRotation() {
		List<Dot> dotsAfterRotation = new ArrayList<>();
		if(position == LFigurePositions.HOR_LEFT) {
			Dot init = dots.get(0);
			buildVertHigh(init.getX(), init.getY(), dotsAfterRotation);
		} else if(position == LFigurePositions.VERT_HIGH) {
			Dot init = dots.get(0);
			buildHorRight(init.getX(), init.getY(), dotsAfterRotation);
		} else if(position == LFigurePositions.HOR_RIGHT) {
			int initX = dots.get(0).getX();
			int initY = dots.get(0).getY()-1;
			buildVertLow(initX, initY, dotsAfterRotation);
		} else if(LFigurePositions.VERT_LOW == position) {
			Dot init = dots.get(0);
			buildHorLeft(init.getX(), init.getY(), dotsAfterRotation);
		}
		return dotsAfterRotation;
	}

	@Override
	public List<Dot> getDots() {
		return dots;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dots == null) ? 0 : dots.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
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
		LFigureLeft other = (LFigureLeft) obj;
		if (dots == null) {
			if (other.dots != null)
				return false;
		} else if (!dots.equals(other.dots))
			return false;
		if (position != other.position)
			return false;
		return true;
	}
	
	
}
