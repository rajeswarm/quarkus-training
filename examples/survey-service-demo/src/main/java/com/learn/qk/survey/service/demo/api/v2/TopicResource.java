package com.learn.qk.survey.service.demo.api.v2;

import java.net.URI;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.learn.qk.survey.service.demo.dto.TopicDTO;
import com.learn.qk.survey.service.demo.services.TopicService;

@Path("/v2/topics")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TopicResource {
	
	@Inject
	TopicService topicService;
	
	@POST
	public Response createTopic(@Valid TopicDTO topicDTO) {
		var createdTopic = topicService.createTopic(topicDTO);
		return Response.created(URI.create("/v2/topics/"+ createdTopic.getTopicId())).build();
	}
	
	@GET
	@Path("/{id}")
	public TopicDTO getTopicById(@PathParam("id") int id) {
		var topicOptional = topicService.getTopicById(id);
		
		if(topicOptional.isEmpty()) {
			throw new NotFoundException();
		}
		
		return topicOptional.get();
	}

}
