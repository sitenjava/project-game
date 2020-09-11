package com.game.data.dto;

public class QuestionsDto extends BaseDto<QuestionsDto> {
	
	private String question;
	private String answer;
	private Boolean active;
	private Integer horizontal;
	private Integer vertical;
	private GameDto game;
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Integer getHorizontal() {
		return horizontal;
	}
	public void setHorizontal(Integer horizontal) {
		this.horizontal = horizontal;
	}
	public Integer getVertical() {
		return vertical;
	}
	public void setVertical(Integer vertical) {
		this.vertical = vertical;
	}
	public GameDto getGame() {
		return game;
	}
	public void setGame(GameDto game) {
		this.game = game;
	}
	
	
}
