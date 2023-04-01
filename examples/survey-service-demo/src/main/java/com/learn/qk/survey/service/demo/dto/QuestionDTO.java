package com.learn.qk.survey.service.demo.dto;

import javax.validation.constraints.NotNull;

public class QuestionDTO {
	private Integer questionId;
	
	@NotNull
	private String questionText;
	private int questionSequence;
	private int topicId;
	
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public int getQuestionSequence() {
		return questionSequence;
	}
	public void setQuestionSequence(int questionSequence) {
		this.questionSequence = questionSequence;
	}
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
}
