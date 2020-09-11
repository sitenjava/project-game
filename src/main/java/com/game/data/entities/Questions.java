package com.game.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "questions")
public class Questions extends BaseEntity {
	
	@Column(name = "question")
	private String question;
	
	@Column(name = "answer")
	private String answer;
	
	@Column(name = "active")
	private Boolean active;
	
	@Column(name = "horizontal")
	private Integer horizontal;
	
	@Column(name = "vertical")
	private Integer vertical;
	
	@ManyToOne
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

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
