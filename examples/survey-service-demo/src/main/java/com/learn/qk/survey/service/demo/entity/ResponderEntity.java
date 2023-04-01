package com.learn.qk.survey.service.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "RESPONDERS")
public class ResponderEntity extends PanacheEntityBase{

	@Id
	@Column(name = "RESPONDER_EMAIL", length = 100)
	@NotNull
	private String responderEmail;
	
	@Column(name = "RESPONDER_NAME", length = 200)
	@NotNull
	private String responderName;
	
	@OneToMany(mappedBy = "responderEntity")
	private List<ResponseEntity> responseEntities;

	public String getResponderEmail() {
		return responderEmail;
	}

	public void setResponderEmail(String responderEmail) {
		this.responderEmail = responderEmail;
	}

	public String getResponderName() {
		return responderName;
	}

	public void setResponderName(String responderName) {
		this.responderName = responderName;
	}

	public List<ResponseEntity> getResponseEntities() {
		return responseEntities;
	}

	public void setResponseEntities(List<ResponseEntity> responseEntities) {
		this.responseEntities = responseEntities;
	}
}
