package com.learn.qk.survey.service.demo.reactive.api.v1;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.learn.qk.survey.service.demo.reactive.dao.SurveyDAO;
import com.learn.qk.survey.service.demo.reactive.dto.TopicDTO;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("/v1/topics")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TopicResource {
	
	@Inject
	SurveyDAO<TopicDTO, Integer> topicDAO;
	
	@POST
	public Uni<TopicDTO> createTopic(TopicDTO topicDTO) {
		return topicDAO.insert(topicDTO);
	}
	
	@GET
	@Path("/{id}")
	public Uni<TopicDTO> getTopicById(@PathParam("id") int id) {
		return topicDAO.getById(id);
	}
	
	@GET
	public Multi<TopicDTO> getallTopics() {
		return topicDAO.getAll();
	}

}
