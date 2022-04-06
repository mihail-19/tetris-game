package com.teslenko.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class LineFigureTest {
	
	@Test
	public void testCreationFailTooSmalLength() {
		assertThrows(IllegalArgumentException.class, () -> new LineFigure("", 1, true));
	}
	@Test
	public void testHorizontalCreation() {
		Figure line = new LineFigure("", 2, true);
		assertEquals(2, line.getDots().size());
		assertEquals(0, line.getDots().get(0).getX());
		assertEquals(0, line.getDots().get(0).getY());
		assertEquals(1, line.getDots().get(1).getX());
		assertEquals(0, line.getDots().get(1).getY());
	}
	@Test
	public void testVerticalCreation() {
		Figure line = new LineFigure("", 3, false);
		assertEquals(3, line.getDots().size());
		assertEquals(0, line.getDots().get(0).getX());
		assertEquals(0, line.getDots().get(0).getY());
		assertEquals(0, line.getDots().get(1).getX());
		assertEquals(1, line.getDots().get(1).getY());
		assertEquals(0, line.getDots().get(2).getX());
		assertEquals(2, line.getDots().get(2).getY());
	}
	
	@Test
	public void testRotateVertical() {
		Figure line = new LineFigure("", 2, false);
		line.rotate();
		assertEquals(0, line.getDots().get(0).getX());
		assertEquals(0, line.getDots().get(0).getY());
		assertEquals(1, line.getDots().get(1).getX());
		assertEquals(0, line.getDots().get(1).getY());
	}
	
	@Test
	public void testRotateHorizontal() {
		Figure line = new LineFigure("", 2, true);
		line.rotate();
		assertEquals(0, line.getDots().get(0).getX());
		assertEquals(0, line.getDots().get(0).getY());
		assertEquals(0, line.getDots().get(1).getX());
		assertEquals(1, line.getDots().get(1).getY());
	}
}
