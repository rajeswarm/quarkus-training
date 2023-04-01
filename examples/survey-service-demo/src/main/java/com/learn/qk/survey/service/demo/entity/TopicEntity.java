package com.learn.qk.survey.service.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "TOPICS")
public class TopicEntity extends PanacheEntityBase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TOPIC_ID")
	private int topicId;
	
	@NotNull
	@Column(name = "TOPIC_NAME", length = 250)
	private String topicName;
	
	@Column(name = "TOPIC_SEQUENCE", unique = true)
	@Min(1)
	private int topicSequence;
	
	@OneToMany(mappedBy = "topicEntity")
	private List<QuestionEntity> questions;

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public int getTopicSequence() {
		return topicSequence;
	}

	public void setTopicSequence(int topicSequence) {
		this.topicSequence = topicSequence;
	}

	public List<QuestionEntity> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionEntity> questions) {
		this.questions = questions;
	}
}
