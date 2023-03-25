package com.learn.qk.cdi.demo.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.learn.qk.cdi.demo.annotations.Preferred;
import com.learn.qk.cdi.demo.dao.SurveyDataAccess;
import com.learn.qk.cdi.demo.dao.TopicDataAccess;
import com.learn.qk.cdi.demo.dao.UserDataAccess;

import io.quarkus.arc.Unremovable;

@Preferred
@ApplicationScoped
@Unremovable
public class SurveyServiceImpl implements SurveyService{
	
	@Inject
	SurveyDataAccess surveyDataAccess;
	
	private UserDataAccess userDataAccess;
	
	private TopicDataAccess topicDataAccess;
	
	SurveyServiceImpl(UserDataAccess userDataAccess){
		this.userDataAccess = userDataAccess;
	}
	
	@Inject
	void setTopicDataAccess(TopicDataAccess topicDataAccess) {
		this.topicDataAccess = topicDataAccess;
	}
	

	@Override
	public void createSurvey() {
		
	}

}
