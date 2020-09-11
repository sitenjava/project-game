package com.game.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "puzzle_log")
public class PuzzleLog extends BaseEntity {
	@Column(name = "active_question")
	private Boolean activeQuestion;
	
	@Column(name = "time")
	private Integer time;
	
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Questions questionID;

	public Boolean getActiveQuestion() {
		return activeQuestion;
	}

	public void setActiveQuestion(Boolean activeQuestion) {
		this.activeQuestion = activeQuestion;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Questions getQuestionID() {
		return questionID;
	}

	public void setQuestionID(Questions questionID) {
		this.questionID = questionID;
	}

	
}
