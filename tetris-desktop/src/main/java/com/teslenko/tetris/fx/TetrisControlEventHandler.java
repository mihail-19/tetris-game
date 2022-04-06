package com.teslenko.tetris.fx;

import com.teslenko.tetris.model.GameProcess;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TetrisControlEventHandler implements EventHandler<KeyEvent>{
	private GameProcess gameProcess;
	
	public TetrisControlEventHandler(GameProcess gameProcess) {
		super();
		this.gameProcess = gameProcess;
	}

	@Override
	public void handle(KeyEvent e) {
		if (e.getCode() == KeyCode.LEFT) {
			gameProcess.moveLeft();
		} else if (e.getCode() == KeyCode.RIGHT) {
			gameProcess.moveRight();
		} else if(e.getCode() == KeyCode.DOWN) {
			gameProcess.moveDown();
		} else if(e.getCode() == KeyCode.UP) {
			gameProcess.rotateRight();
		}
		
	}

}
