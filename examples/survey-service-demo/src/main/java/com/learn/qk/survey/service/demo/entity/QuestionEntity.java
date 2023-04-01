package com.learn.qk.survey.service.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "QUESTIONS")
public class QuestionEntity extends PanacheEntityBase {
	
	@Id
	@Column(name = "QUESTION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionId;
	
	@Column(name = "QUESTION_TEXT", length = 2000)
	@NotNull
	private String questionText;
	
	@Column(name = "QUESTION_SEQUENCE", unique = true)
	@Min(1)
	private int questionSequence;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "TOPIC_ID", updatable = false, nullable = false)
	private TopicEntity topicEntity;
	
	@OneToMany(mappedBy = "questionEntity")
	private List<ResponseEntity> responseEntities;

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
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

	public TopicEntity getTopicEntity() {
		return topicEntity;
	}

	public void setTopicEntity(TopicEntity topicEntity) {
		this.topicEntity = topicEntity;
	}
}
