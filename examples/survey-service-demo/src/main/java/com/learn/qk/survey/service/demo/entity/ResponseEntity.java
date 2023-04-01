package com.learn.qk.survey.service.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "RESPONSES")
public class ResponseEntity extends PanacheEntityBase {
	
	@Id
	@Column(name = "RESPONSE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int responseId;
	
	@Column(name = "RESPONSE_TEXT", length = 512)
	@NotNull
	private String responseText;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "RESPONDER_EMAIL", nullable = false, updatable = false)
	private ResponderEntity responderEntity;
	
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "QUESTION_ID", nullable = false, updatable = false)
	private QuestionEntity questionEntity;

}
