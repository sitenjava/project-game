package com.game.data.dto;

import com.game.data.entities.Game;

public class QuestionDto extends BaseDto<QuestionDto> {
	
	private String question;
	private String answer;
	private Boolean active;
	private Integer startHorizontal;
	private Integer startVertical;
	private Integer endVertical;
	private Integer endHorizontal;
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

	public Integer getStartHorizontal() {
		return startHorizontal;
	}

	public void setStartHorizontal(Integer startHorizontal) {
		this.startHorizontal = startHorizontal;
	}

	public Integer getStartVertical() {
		return startVertical;
	}

	public void setStartVertical(Integer startVertical) {
		this.startVertical = startVertical;
	}

	public Integer getEndVertical() {
		return endVertical;
	}

	public void setEndVertical(Integer endVertical) {
		this.endVertical = endVertical;
	}

	public Integer getEndHorizontal() {
		return endHorizontal;
	}

	public void setEndHorizontal(Integer endHorizontal) {
		this.endHorizontal = endHorizontal;
	}

	public GameDto getGame() {
		return game;
	}

	public void setGame(GameDto game) {
		this.game = game;
	}
}
