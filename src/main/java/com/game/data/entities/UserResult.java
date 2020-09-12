package com.game.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_result")
public class UserResult extends BaseEntity {
	
	@Column(name = "client_answer" , columnDefinition = "text")
	private String clientAnswer;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private Question question;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	@Column(name = "active")
	private Boolean active;
	@Column(name = "time")
	private Integer time;

	public String getClientAnswer() {
		return clientAnswer;
	}

	public void setClientAnswer(String clientAnswer) {
		this.clientAnswer = clientAnswer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}
}
