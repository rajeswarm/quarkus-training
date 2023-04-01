package com.learn.qk.survey.service.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.learn.qk.survey.service.demo.dto.QuestionDTO;
import com.learn.qk.survey.service.demo.entity.QuestionEntity;
import com.learn.qk.survey.service.demo.repositories.QuestionRepository;
import com.learn.qk.survey.service.demo.repositories.TopicRepository;

@ApplicationScoped
class QuestionServiceImpl implements QuestionService{
	
	@Inject
	QuestionRepository questionRepository;
	
	@Inject
	TopicRepository topicRepository;

	@Transactional
	@Override
	public QuestionDTO createQuestion(QuestionDTO questionDTO) {
		QuestionEntity questionEntity = new QuestionEntity();
		questionEntity.setQuestionText(questionDTO.getQuestionText());
		questionEntity.setQuestionSequence(questionDTO.getQuestionSequence());
		
		var topicEntityOptional = topicRepository.findByIdOptional(questionDTO.getTopicId());
		if(topicEntityOptional.isEmpty()) {
			throw new IllegalArgumentException("Topic with the provided Id does not exist");
		}
		
		
		questionEntity.setTopicEntity(topicEntityOptional.get());
		questionRepository.persist(questionEntity);
		
		questionDTO.setQuestionId(questionEntity.getQuestionId());
		
		return questionDTO;
	}

	@Override
	public Optional<QuestionDTO> getQuestionById(int questionId) {
		var questionEntityOptional= questionRepository.findByIdOptional(questionId);
		
		if(questionEntityOptional.isEmpty()) {
			return Optional.empty();
		}
		
		QuestionDTO questionDTO = convertEntityToDTO(questionEntityOptional.get());
		
		return Optional.of(questionDTO);
	}

	private QuestionDTO convertEntityToDTO(QuestionEntity questionEntity) {
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setQuestionId(questionEntity.getQuestionId());
		questionDTO.setQuestionSequence(questionEntity.getQuestionSequence());
		questionDTO.setQuestionText(questionEntity.getQuestionText());
		questionDTO.setTopicId(questionEntity.getTopicEntity().getTopicId());
		return questionDTO;
	}

	@Override
	public List<QuestionDTO> getQuestionsByTopicId(int topicId) {
		List<QuestionEntity> questionEntities = questionRepository.getQuestionsByTopic(topicId);
		
		List<QuestionDTO> questionDTOs = new ArrayList<>();
		for(var entity: questionEntities) {
			QuestionDTO questionDTO = convertEntityToDTO(entity);
			questionDTOs.add(questionDTO);
		}
		
		return questionDTOs;
	}

	@Transactional
	@Override
	public void updateQuestion(QuestionDTO questionDTO) {
		var questionEntityOptional = questionRepository.findByIdOptional(questionDTO.getQuestionId());
		if(questionEntityOptional.isEmpty()) {
			throw new IllegalArgumentException("Question does not exist");
		}
		
		QuestionEntity questionEntity = questionEntityOptional.get();
		questionEntity.setQuestionText(questionDTO.getQuestionText());
		
		questionRepository.persist(questionEntity);
	}
	
	@Transactional
	@Override
	public boolean deleteQuestion(int questionId) {
		return questionRepository.deleteById(questionId);
	}
}
