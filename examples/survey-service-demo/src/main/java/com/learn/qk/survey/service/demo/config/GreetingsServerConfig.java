package com.learn.qk.survey.service.demo.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "greetings.server")
public interface GreetingsServerConfig {

	@JsonProperty
	String host();
	
	@JsonProperty
	Integer port();
	
	@JsonProperty
	Log log();
	
	interface Log {
		
		@JsonProperty
		Boolean enabled();
		
		@JsonProperty
		String fileName();
		
	}
}
