package com.game.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "questions")
public class Question extends BaseEntity {
	
	@Column(name = "question" , columnDefinition = "text")
	private String question;
	@Column(name = "answer" , columnDefinition = "text")
	private String answer;
	@Column(name = "active")
	private Boolean active;
	@Column(name = "start_horizontal")
	private Integer startHorizontal;
	@Column(name = "start_vertical")
	private Integer startVertical;
	@Column(name = "end_vertical")
	private Integer endVertical;
	@Column(name = "end_horizontal")
	private Integer endHorizontal;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_id")
	private Game game;

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

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
