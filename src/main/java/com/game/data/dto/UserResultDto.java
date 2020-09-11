package com.game.data.dto;

public class UserResultDto extends BaseDto<UserResultDto> {
	private String clientAnswer;
	private QuestionsDto question;
	private UserDto user;
	public String getClientAnswer() {
		return clientAnswer;
	}
	public void setClientAnswer(String clientAnswer) {
		this.clientAnswer = clientAnswer;
	}
	public QuestionsDto getQuestion() {
		return question;
	}
	public void setQuestion(QuestionsDto question) {
		this.question = question;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	
	
}
