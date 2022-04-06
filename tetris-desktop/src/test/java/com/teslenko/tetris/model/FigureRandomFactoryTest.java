package com.teslenko.tetris.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class FigureRandomFactoryTest {
	FigureRandomFactory factory = new FigureRandomFactory(10);
	@Test
	public void testContainsAllFigures() {
		List<Figure> figures = new ArrayList<>();
		int size = 100;
		for(int i = 0; i<size; i++) {
			figures.add(factory.getFigure());
		}
		assertEquals(size, figures.size());
		List<Class> classes = figures.stream().map((f) -> f.getClass()).collect(Collectors.toList());
		assertTrue(classes.contains(DotFigure.class));
		assertTrue(classes.contains(LineFigure.class));
		assertTrue(classes.contains(CubeFigure.class));
		assertTrue(classes.contains(LFigureLeft.class));
		assertTrue(classes.contains(LFigureRight.class));
		
	}
}
