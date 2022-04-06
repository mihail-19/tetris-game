package com.teslenko.tetris.fx;

import java.util.List;
import java.util.stream.Collectors;

import com.teslenko.tetris.model.Dot;
import com.teslenko.tetris.model.Figure;
import com.teslenko.tetris.model.TetrisController;

/**
 * Tetris controller to communicate between model and JavaFX interface
 * @author Mykhailo Teslenko
 *
 */
public class TetrisFxController implements TetrisController{
	private MainWindow mainWindow;
	
	/**
	 * Communication through main {@link MainWindow} methods for graphic painting.
	 * @param mainWindow
	 */
	public TetrisFxController(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	@Override
	public void paintAllDots(List<Figure> surfaceFigures, Figure figure) {
		mainWindow.paint(figure, surfaceFigures);
	}

	@Override
	public void paintScore(int score) {
		mainWindow.paintScore(score);
	}

	@Override
	public void paintGameOver() {
		mainWindow.paintGameOver();
	}
	
	
}
