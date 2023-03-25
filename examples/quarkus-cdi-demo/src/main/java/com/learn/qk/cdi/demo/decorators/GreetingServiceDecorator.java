
package com.learn.qk.cdi.demo.decorators;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

import com.learn.qk.cdi.demo.services.GreetingService;

import io.quarkus.arc.Priority;

@Decorator
@Priority(10)
public class GreetingServiceDecorator implements GreetingService {

	@Inject
	@Delegate
	GreetingService greetingService;

	@Override
	public String sayHello(String name) {
		String originalHello = greetingService.sayHello(name);
		return "Decorated - " + originalHello;
	}
}
