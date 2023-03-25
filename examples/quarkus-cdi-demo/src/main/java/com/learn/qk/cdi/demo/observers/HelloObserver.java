package com.learn.qk.cdi.demo.observers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class HelloObserver {
	
	
	void onHelloSaid(@Observes String audience) {
		System.out.println("Observed hello said to "+ audience);
	}

}
