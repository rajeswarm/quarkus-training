package com.learn.qk.survey.service.demo;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.learn.qk.survey.service.demo.dto.QuestionDTO;
import com.learn.qk.survey.service.demo.entity.QuestionEntity;
import com.learn.qk.survey.service.demo.entity.TopicEntity;
import com.learn.qk.survey.service.demo.repositories.QuestionRepository;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
public class QuestionResourceTest {
	
	@InjectMock
	QuestionRepository questionRepository;
	
	@Test
	void testGetQuestionById() {
		
		int questionId = 100;
		
		TopicEntity topicEntity = new TopicEntity();
		topicEntity.setTopicId(1);
		
		QuestionEntity questionEntity = new QuestionEntity();
		questionEntity.setQuestionId(questionId);
		questionEntity.setQuestionText("What is this question?");
		questionEntity.setQuestionSequence(10);
		questionEntity.setTopicEntity(topicEntity);
		
		Mockito.when(questionRepository.findByIdOptional(questionId)).thenReturn(Optional.of(questionEntity));
		
		var returnedQuestion = given().when().get("/v2/questions/"+ questionId).as(QuestionDTO.class);
		assertNotNull(returnedQuestion);
		assertEquals(questionEntity.getQuestionId(), returnedQuestion.getQuestionId());
		assertEquals(questionEntity.getQuestionSequence(), returnedQuestion.getQuestionSequence());
		assertEquals(questionEntity.getQuestionText(), returnedQuestion.getQuestionText());
		assertEquals(topicEntity.getTopicId(), returnedQuestion.getTopicId());
	}
	
	@Test
	void testNonExistentGetQuestionById() {
		
		int questionId = 200;
		
		Mockito.when(questionRepository.findByIdOptional(questionId)).thenReturn(Optional.empty());
		
		given().when().get("/v2/questions/"+ questionId).then().statusCode(404);
	}

}
