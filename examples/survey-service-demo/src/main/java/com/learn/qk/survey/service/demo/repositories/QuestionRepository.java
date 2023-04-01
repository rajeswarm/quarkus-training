package com.learn.qk.survey.service.demo.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.learn.qk.survey.service.demo.entity.QuestionEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class QuestionRepository implements PanacheRepositoryBase<QuestionEntity, Integer>{
	
	public List<QuestionEntity> getQuestionsByTopic(int topicId){
		return list("topicEntity.topicId", topicId);
	}
}
