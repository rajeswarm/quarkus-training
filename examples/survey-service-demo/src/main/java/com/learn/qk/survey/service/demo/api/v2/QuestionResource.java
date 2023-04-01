package com.learn.qk.survey.service.demo.api.v2;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.learn.qk.survey.service.demo.dto.QuestionDTO;
import com.learn.qk.survey.service.demo.services.QuestionService;

@Path("/v2/questions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class QuestionResource {
	
	@Inject
	QuestionService questionService;
	
	@POST
	public Response createQuestion(@Valid QuestionDTO questionDTO) {
		var createdQuestionDTO = questionService.createQuestion(questionDTO);
		
		return Response.created(URI.create("/v2/questions/"+createdQuestionDTO.getQuestionId())).build();
	}
	
	@GET
	@Path("/{id}")
	public QuestionDTO getQuestionById(@PathParam("id") int questionId) {
		var questionOptional = questionService.getQuestionById(questionId);
		if(questionOptional.isEmpty()) {
			throw new NotFoundException();
		}
		
		return questionOptional.get();
	}
	
	@GET
	@Path("/by-topic/{topicId}")
	public List<QuestionDTO> getQuestionsByTopicId(@PathParam("topicId") int topicId){
		return questionService.getQuestionsByTopicId(topicId);
	}
	
	@PUT
	public Response updateQuestion(@Valid QuestionDTO questionDTO) {
		questionService.updateQuestion(questionDTO);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteQuestion(@PathParam("id") int questionId) {
		var isDeleted = questionService.deleteQuestion(questionId);
		if(!isDeleted) {
			throw new NotFoundException();
		}
		
		return Response.ok().build();
	}
	
}
