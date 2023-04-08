package com.learn.qk.survey.service.demo;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.learn.qk.survey.service.demo.dto.TopicDTO;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;

@QuarkusTest
public class TopicResourceTest {

	@Test
	public void testCreateTopicEndpoint() {
		TopicDTO topicToCreate = new TopicDTO();
		topicToCreate.setTopicName("Java Programming");
		topicToCreate.setTopicSequence(100);

		given().when().body(topicToCreate, ObjectMapperType.JACKSON_2).
		contentType(ContentType.JSON).post("/v2/topics")
				.then().statusCode(201);
	}
	
	@Test
	public void testGetTopicByIdEndpoint() {
		TopicDTO topicToCreate = new TopicDTO();
		topicToCreate.setTopicName(".NET Programming");
		topicToCreate.setTopicSequence(110);
		
		//create topic
		RequestSpecification createRequest = RestAssured.given(); 
		var response = createRequest.body(topicToCreate, ObjectMapperType.JACKSON_2).contentType(ContentType.JSON).post("/v2/topics");
		assertEquals(201, response.getStatusCode());
		
		//get location header
		var location = response.getHeader("location");
		assertNotNull(location);
		
		var returnedTopic = given().when().get(location).as(TopicDTO.class);
		
		assertNotNull(returnedTopic);
		assertEquals(topicToCreate.getTopicName(), returnedTopic.getTopicName());
		assertEquals(topicToCreate.getTopicSequence(), returnedTopic.getTopicSequence());
	}

}
