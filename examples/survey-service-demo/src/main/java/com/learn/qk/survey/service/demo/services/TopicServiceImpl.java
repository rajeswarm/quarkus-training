package com.learn.qk.survey.service.demo.services;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.learn.qk.survey.service.demo.dto.TopicDTO;
import com.learn.qk.survey.service.demo.entity.TopicEntity;
import com.learn.qk.survey.service.demo.repositories.TopicRepository;

@ApplicationScoped
class TopicServiceImpl implements TopicService {
	
	@Inject
	TopicRepository topicRepository;

	@Transactional
	@Override
	public TopicDTO createTopic(TopicDTO topicDTO) {
		
		if(topicDTO.getTopicId() != null) {
			throw new IllegalArgumentException("Topic Id must not be set for new topics");
		}
		
		if(topicDTO.getTopicSequence() == null) {
			throw new IllegalArgumentException("Topic Sequence must not be null");
		}
		
		TopicEntity topicEntity = new TopicEntity();
		topicEntity.setTopicName(topicDTO.getTopicName());
		topicEntity.setTopicSequence(topicDTO.getTopicSequence());
		
		topicRepository.persist(topicEntity);
		
		//persist creates the topic Id
		topicDTO.setTopicId(topicEntity.getTopicId());
		
		return topicDTO;
	}

	@Override
	public Optional<TopicDTO> getTopicById(int topicId) {
		Optional<TopicEntity> topicEntityOptional = topicRepository.findByIdOptional(topicId);
		if(topicEntityOptional.isEmpty()) {
			return Optional.empty();
		}
		
		var topicEntity = topicEntityOptional.get();
		TopicDTO topicDTO = convertEntityToDTO(topicEntity);
		
		return Optional.of(topicDTO);
	}

	private TopicDTO convertEntityToDTO(TopicEntity topicEntity) {
		TopicDTO topicDTO = new TopicDTO();
		topicDTO.setTopicId(topicEntity.getTopicId());
		topicDTO.setTopicName(topicEntity.getTopicName());
		topicDTO.setTopicSequence(topicEntity.getTopicSequence());
		return topicDTO;
	}

}
