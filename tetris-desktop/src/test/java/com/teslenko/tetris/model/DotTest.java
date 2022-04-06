package com.teslenko.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DotTest {
	Dot dot = new Dot();
	@Test
	public void ifMoveGetDownYChanges() {
		assertEquals(0, dot.getY());
		dot.moveDown();
		assertEquals(1, dot.getY());
		dot.moveDown();
		assertEquals(2, dot.getY());
	}
	
	@Test
	public void ifMoveUpYChanges() {
		assertEquals(0, dot.getY());
		dot.moveUp();
		assertEquals(-1, dot.getY());
		dot.moveUp();
		assertEquals(-2, dot.getY());
	}
	
	@Test
	public void ifMoveLeftXChanges() {
		assertEquals(0, dot.getX());
		dot.moveLeft();
		assertEquals(-1, dot.getX());
		dot.moveLeft();
		assertEquals(-2, dot.getX());
	}
	
	@Test
	public void ifMoveRightXChanges() {
		assertEquals(0, dot.getX());
		dot.moveRight();
		assertEquals(1, dot.getX());
		dot.moveRight();
		assertEquals(2, dot.getX());
	}
}
