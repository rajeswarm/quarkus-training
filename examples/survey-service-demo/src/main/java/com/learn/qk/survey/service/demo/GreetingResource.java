package com.learn.qk.survey.service.demo;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.learn.qk.survey.service.demo.config.CustomValue;
import com.learn.qk.survey.service.demo.config.GreetingsServerConfig;

import io.smallrye.config.SmallRyeConfig;

@Path("/hello")
public class GreetingResource {

	@ConfigProperty(name = "greetings.message")
	String greetingsMessage;

	@ConfigProperty(name = "greetings.suffix", defaultValue = "!")
	String suffix;

	@ConfigProperty(name = "greetings.name")
	Optional<String> name;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "Hello from RESTEasy Reactive";
	}

	@GET
	@Path("/config")
	@Produces(MediaType.TEXT_PLAIN)
	public String helloFromConfig() {
		String message = String.format("%s %s %s", greetingsMessage, suffix, name.orElseGet(() -> ""));
		return message;
	}

	@GET
	@Path("/programmatic-config")
	@Produces(MediaType.TEXT_PLAIN)
	public String helloFromProgrammaticConfig() {

		String message = ConfigProvider.getConfig().getValue("greetings.message", String.class);

		return message;
	}

	@GET
	@Path("/expression-config")
	@Produces(MediaType.TEXT_PLAIN)
	public String helloFromExpressionConfig() {
		String message = ConfigProvider.getConfig().getValue("greetings.full-message", String.class);
		return message;
	}

	@GET
	@Path("/pets")
	@Produces(MediaType.TEXT_PLAIN)
	public List<String> helloPets() {
		List<String> pets = ConfigProvider.getConfig().getValues("pets.collection", String.class);
		return pets;
	}

	@Inject
	GreetingsServerConfig greetingsServerConfig;

	@GET
	@Path("/server-config")
	@Produces(MediaType.APPLICATION_JSON)
	public GreetingsServerConfig getServerConfig() {
		// return greetingsServerConfig;

		SmallRyeConfig smallRyeConfig = ConfigProvider.getConfig().unwrap(SmallRyeConfig.class);
		return smallRyeConfig.getConfigMapping(GreetingsServerConfig.class);
	}

	@GET
	@Path("/config/{propertyName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getFromCustomConfigSource(@PathParam(value = "propertyName") String propertyName) {
		return ConfigProvider.getConfig().getValue(propertyName, String.class);
	}

	@GET
	@Path("/config/custom-value/{propertyName}")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomValue getFromCustomValueFromConfigSource(@PathParam(value = "propertyName") String propertyName) {
		return ConfigProvider.getConfig().getValue(propertyName, CustomValue.class);
	}

}