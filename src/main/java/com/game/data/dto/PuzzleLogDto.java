package com.game.data.dto;

public class PuzzleLogDto extends BaseDto<PuzzleLogDto> {
	private Boolean activeQuestion;
	private Integer time;
	private QuestionDto questionID;
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
	public QuestionDto getQuestionID() {
		return questionID;
	}
	public void setQuestionID(QuestionDto questionID) {
		this.questionID = questionID;
	}
	
	
}
