package com.teslenko.tetris;

import com.teslenko.tetris.fx.FxGameProcess;
import com.teslenko.tetris.fx.GameSpeed;
import com.teslenko.tetris.fx.MainWindow;
import com.teslenko.tetris.fx.TetrisControlEventHandler;
import com.teslenko.tetris.fx.TetrisFxController;
import com.teslenko.tetris.model.FigureRandomFactory;
import com.teslenko.tetris.model.GameField;
import com.teslenko.tetris.model.GameProcess;
import com.teslenko.tetris.model.TetrisController;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Tetris game main class - JavaFX application.
 * Opens a new window, game process instantly started.
 * Game over if any block position is equal to the top side of game field. 
 *
 */
public class App extends Application {
	public static final int DOT_SIZE = 25;
	public static final int ROWS = 20;
	public static final int COLUMNS = 12;
	public static final int TOP_BOX_HEIGHT = 30;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox();
		BorderPane top = new BorderPane();
		top.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, new Insets(0))));
		top.setMinHeight(TOP_BOX_HEIGHT);
		top.setMaxHeight(TOP_BOX_HEIGHT);
		MainWindow mainWindow = new MainWindow(root, top);
		TetrisController controller = new TetrisFxController(mainWindow);
		GameProcess gameProcess = new FxGameProcess(mainWindow,
				new GameField(ROWS, COLUMNS), 
				controller, 
				new FigureRandomFactory(COLUMNS),
				GameSpeed.FIRST);
		gameProcess.start();
		Scene scene = new Scene(root, COLUMNS*DOT_SIZE, TOP_BOX_HEIGHT + ROWS * DOT_SIZE);
		scene.setOnKeyPressed(new TetrisControlEventHandler(gameProcess));
		stage.setScene(scene);
		stage.setTitle("Tetris");
		stage.setResizable(false);
		stage.show();
	}
}
