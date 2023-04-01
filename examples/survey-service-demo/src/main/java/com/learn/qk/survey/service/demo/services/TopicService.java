package com.learn.qk.survey.service.demo.services;

import java.util.Optional;

import com.learn.qk.survey.service.demo.dto.TopicDTO;

public interface TopicService {

	TopicDTO createTopic(TopicDTO topicDTO);
	Optional<TopicDTO>  getTopicById(int topicId);
}
