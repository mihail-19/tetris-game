package com.teslenko.tetris.fx;

import javafx.scene.control.Label;

public class ScoreLabel extends Label{
	private String prompt;
	private int score = 0;
	public ScoreLabel(String prompt, int score) {
		super(prompt + score);
		this.prompt = prompt;
		this.score = score;
		
	}
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
		super.setText(fullText());
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
		super.setText(fullText());
	}
	public String fullText() {
		return prompt + score;
	}
	
}
