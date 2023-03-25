package com.learn.qk.cdi.demo.services;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.learn.qk.cdi.demo.annotations.Logged;
import com.learn.qk.cdi.demo.utils.Util;

@ApplicationScoped
class GreetingServiceImpl implements GreetingService{
	
	@Inject
	Event<String> saidHello;
	
	@PostConstruct
	void postConstruct() {
		System.out.println("Creating instance of GreetingServiceImpl");
	}

	@Override
	@Logged
	public String sayHello(String name) {
		String hello = String.format("Hello %s from GreetingServiceImpl", name);
		Util.doWork();
		saidHello.fire(name);
		return hello;
	}
	
	@PreDestroy
	void preDestroy() {
		System.out.println("Destroying instance of GreetingServiceImpl");
	}
}
