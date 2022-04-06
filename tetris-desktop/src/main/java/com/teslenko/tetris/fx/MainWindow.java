package com.teslenko.tetris.fx;

import java.util.List;

import com.teslenko.tetris.App;
import com.teslenko.tetris.model.Dot;
import com.teslenko.tetris.model.Figure;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MainWindow {
	private Pane pane;
	private Pane innerPane;
	private ScoreLabel scoreLabel;
	private Label gameOver;
	private Label gameSpeedLabel;
	private BorderPane top;
	public MainWindow(Pane root, BorderPane top) {
		this.pane = root;
		this.top = top;
		setupTop();
	}
	
	public void setupTop() {
		pane.getChildren().add(top);
		scoreLabel = new ScoreLabel("Score: ", 0);
		gameOver = new Label("Game Over");
		gameSpeedLabel = new Label(1 + "X");
		gameOver.setTextFill(Color.RED);
		gameOver.setVisible(false);
		VBox topTop = new VBox();
		topTop.setMinHeight(5);
		top.setTop(topTop);
		top.setLeft(scoreLabel);
		top.setRight(gameSpeedLabel);
		top.setPadding(new Insets(0, 10, 0, 10));
	}
	
	public void paint(Figure figure, List<Figure> figures) {
		resetGameField();
		paintFigure(figure);
		figures.forEach(this::paintFigure);
	}
	public void resetGameField() {
		if(innerPane != null) {
			pane.getChildren().remove(innerPane);
		}
		innerPane = new Pane();
		pane.getChildren().add(innerPane);
	}
	public void paintFigure(Figure figure) {
		if(figure == null) {
			return;
		}
		Color color = Color.web(figure.getColor());
		figure.getDots().forEach(d -> paintDot(d, color));
	}
	public void paintDot(Dot dot, Color color) {
		Rectangle rect = new Rectangle();
		rect.setFill(color);
		rect.setHeight(App.DOT_SIZE);
		rect.setWidth(App.DOT_SIZE);
		rect.setX(dot.getX()*App.DOT_SIZE);
		rect.setY(dot.getY()*App.DOT_SIZE);
		innerPane.getChildren().add(rect);
	}
	public void paintScore(int score) {
		scoreLabel.setScore(score);
	}
	public void paintGameOver() {
		gameOver.setVisible(true);
		gameSpeedLabel.setVisible(false);
		top.setRight(gameOver);
	}

	public void paintGameSpeed(GameSpeed gameSpeed) {
		gameSpeedLabel.setText((gameSpeed.ordinal() + 1 ) + "x");
	}
}
