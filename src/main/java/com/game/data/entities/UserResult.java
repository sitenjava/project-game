package com.game.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_result")
public class UserResult extends BaseEntity {
	
	@Column(name = "client_answer")
	private String clientAnswer;
	
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Questions question;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	

	public String getClientAnswer() {
		return clientAnswer;
	}

	public void setClientAnswer(String clientAnswer) {
		this.clientAnswer = clientAnswer;
	}

	public Questions getQuestion() {
		return question;
	}

	public void setQuestion(Questions question) {
		this.question = question;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
