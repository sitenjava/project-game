package com.game.data.dto;

public class UserResultDto extends BaseDto<UserResultDto> {
	private String clientAnswer;
	private QuestionDto question;
	private UserDto user;
	private Boolean active;
	private Integer time;
	public String getClientAnswer() {
		return clientAnswer;
	}
	public void setClientAnswer(String clientAnswer) {
		this.clientAnswer = clientAnswer;
	}
	public QuestionDto getQuestion() {
		return question;
	}
	public void setQuestion(QuestionDto question) {
		this.question = question;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
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
