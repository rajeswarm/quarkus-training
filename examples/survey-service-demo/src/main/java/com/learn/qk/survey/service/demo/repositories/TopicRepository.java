package com.learn.qk.survey.service.demo.repositories;

import javax.enterprise.context.ApplicationScoped;

import com.learn.qk.survey.service.demo.entity.TopicEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class TopicRepository implements PanacheRepositoryBase<TopicEntity, Integer> {

}
