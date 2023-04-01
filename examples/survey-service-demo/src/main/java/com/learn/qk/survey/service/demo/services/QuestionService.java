package com.learn.qk.survey.service.demo.services;

import java.util.List;
import java.util.Optional;

import com.learn.qk.survey.service.demo.dto.QuestionDTO;

public interface QuestionService {
	QuestionDTO createQuestion(QuestionDTO questionDTO);
	Optional<QuestionDTO> getQuestionById(int questionId);
	List<QuestionDTO> getQuestionsByTopicId(int topicId);
	
	void updateQuestion(QuestionDTO questionDTO);
	boolean deleteQuestion(int questionId);
}
