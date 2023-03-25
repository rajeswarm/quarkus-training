package com.learn.qk.cdi.demo.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.learn.qk.cdi.demo.annotations.Logged;

import io.quarkus.arc.Priority;

@Logged
@Priority(2000)
@Interceptor
public class LoggedInterceptor {

	@AroundInvoke
	public Object logInvocation(InvocationContext invocationContext) throws Exception {
		try {
			System.out.println("Invoking method: " + invocationContext.getMethod().getName());
			return invocationContext.proceed();

		} finally {
			System.out.println("Completed invocation of method: " + invocationContext.getMethod().getName());
		}

	}

}
