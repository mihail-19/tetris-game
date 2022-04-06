package com.teslenko.tetris.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

public class GameFieldTest {
	public static final int FIELD_WIDTH = 10;
	public static final int FIELD_HEIGHT = 10;
	GameField gameField = new GameField(FIELD_WIDTH, FIELD_HEIGHT);
	
	
	@Test
	public void testReturnAllDots() {
		Figure dot = new DotFigure("", 0);
		Figure line = new LineFigure("", 2, false, 2);
		gameField.setFigure(dot);
		gameField.addCurrentFigureToSurface();
		gameField.setFigure(line);
		gameField.addCurrentFigureToSurface();
		List<Dot> surfaceDots = gameField.getSurfaceDots();
		assertEquals(3, surfaceDots.size());
		//Dot
		assertEquals(0, surfaceDots.get(0).getX());
		assertEquals(0, surfaceDots.get(0).getY());
		//Line
		assertEquals(2, surfaceDots.get(1).getX());
		assertEquals(0, surfaceDots.get(1).getY());
		assertEquals(2, surfaceDots.get(2).getX());
		assertEquals(1, surfaceDots.get(2).getY());
	}
	@Test
	public void couldNotMoveIfNullFigure() {
		assertNull(gameField.getFigure());
		assertFalse(gameField.couldMoveDown());
		assertFalse(gameField.couldMoveLeft());
		assertFalse(gameField.couldMoveRight());
	}
	
	@Test
	public void getFigureDotsIfNullFigure() {
		assertNull(gameField.getFigure());
		assertNotNull(gameField.getFigureDots());
		assertEquals(0, gameField.getFigureDots().size());
	}
	
	@Test
	public void testFailToAddOutOfBordersFigure() {
		Figure dotRight = new DotFigure("", FIELD_WIDTH);
		Figure dotLeft = new DotFigure("", -1);
		Figure dotTop = new DotFigure("", 0);
		dotTop.getDots().get(0).moveUp();
		Figure dotBottom = new DotFigure("", 0);
		dotBottom.getDots().get(0).setY(FIELD_HEIGHT);
		assertThrows(IllegalArgumentException.class, () -> gameField.setFigure(dotRight));
		assertThrows(IllegalArgumentException.class, () -> gameField.setFigure(dotLeft));
		assertThrows(IllegalArgumentException.class, () -> gameField.setFigure(dotTop));
		assertThrows(IllegalArgumentException.class, () -> gameField.setFigure(dotBottom));
	}
	@Test
	public void testMoveDownUntilGameFieldBottom() {
		Figure dot = new DotFigure("", 0);
		gameField.setFigure(dot);
		assertTrue(gameField.couldMoveDown());
		gameField.moveDown();
		assertEquals(1, dot.getDots().get(0).getY());
		while(dot.getDots().get(0).getY() < FIELD_HEIGHT-1) {
			assertTrue(gameField.couldMoveDown());
			gameField.moveDown();
		}
		assertFalse(gameField.couldMoveDown());
		gameField.moveDown();
		assertTrue(dot.getDots().get(0).getY() == FIELD_HEIGHT-1);
	}
	@Test
	public void testMoveDownUntilSurfaceDot() {
		Figure dotSufrace = new DotFigure("", 0);
		gameField.setFigure(dotSufrace);
		gameField.moveDown();
		gameField.moveDown();
		gameField.addCurrentFigureToSurface();
		Figure nextFigure = new DotFigure("", 0);
		gameField.setFigure(nextFigure);
		assertTrue(gameField.couldMoveDown());
		gameField.moveDown();
		assertFalse(gameField.couldMoveDown());
	}
	
	@Test
	public void testCouldMoveDownUnderFigure() {
		Figure line = new LineFigure("", 3, true, 0);
		gameField.setFigure(line);
		gameField.addCurrentFigureToSurface();
		Figure dot = new DotFigure("", 4);
		gameField.setFigure(dot);
		gameField.moveDown();
		assertTrue(gameField.couldMoveDown());
		assertTrue(gameField.couldMoveLeft());
		gameField.moveLeft();
		gameField.moveLeft();
		assertTrue(gameField.couldMoveDown());
		assertTrue(gameField.couldMoveLeft());
	}
	@Test
	public void testMoveRightUntilGameFieldRight() {
		Figure dot = new DotFigure("", 0);
		gameField.setFigure(dot);
		assertTrue(gameField.couldMoveRight());
		gameField.moveRight();
		assertEquals(1, dot.getDots().get(0).getX());
		while(dot.getDots().get(0).getX() < FIELD_WIDTH-1) {
			assertTrue(gameField.couldMoveRight());
			gameField.moveRight();
		}
		assertFalse(gameField.couldMoveRight());
		gameField.moveRight();
		assertTrue(dot.getDots().get(0).getX() == FIELD_WIDTH-1);
	}
	@Test
	public void testMoveRightUntilGameFieldLeft() {
		Figure dot = new DotFigure("", FIELD_WIDTH-1);
		gameField.setFigure(dot);
		assertTrue(gameField.couldMoveLeft());
		gameField.moveLeft();
		assertEquals(FIELD_WIDTH-2, dot.getDots().get(0).getX());
		while(dot.getDots().get(0).getX() > 0) {
			assertTrue(gameField.couldMoveLeft());
			gameField.moveLeft();
		}
		assertFalse(gameField.couldMoveLeft());
		gameField.moveLeft();
		assertTrue(dot.getDots().get(0).getX() == 0);
	}
	
	@Test
	public void testRotateWithShiftLeft() {
		Figure line = new LineFigure("", 2, false, FIELD_WIDTH-1);
		gameField.setFigure(line);
		List<Dot> dots = gameField.getFigureDots();
		assertEquals(FIELD_WIDTH-1, dots.get(0).getX());
		assertEquals(FIELD_WIDTH-1, dots.get(0).getX());
		gameField.rotate();
		assertEquals(FIELD_WIDTH-2, dots.get(0).getX());
		assertEquals(FIELD_WIDTH-1, dots.get(1).getX());
	}
	
	@Test
	public void testCouldNotMoveRightThroughFigure() {
		Figure surfaceDot = new DotFigure("", 3);
		gameField.setFigure(surfaceDot);
		gameField.addCurrentFigureToSurface();
		Figure figure = new DotFigure("", 2);
		gameField.setFigure(figure);
		assertFalse(gameField.couldMoveRight());
	}
	@Test
	public void testCouldNotMoveLeftThroughFigure() {
		Figure surfaceDot = new DotFigure("", 2);
		gameField.setFigure(surfaceDot);
		gameField.addCurrentFigureToSurface();
		Figure figure = new DotFigure("", 3);
		gameField.setFigure(figure);
		assertFalse(gameField.couldMoveLeft());
	}
}
