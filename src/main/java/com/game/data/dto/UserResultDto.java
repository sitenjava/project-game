package com.game.data.dto;

public class UserResultDto extends BaseDto<UserResultDto> {
	private String clientAnswer;
	private QuestionDto question;
	private UserDto user;
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
	
	
}
