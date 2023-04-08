package com.learn.qk.survey.service.demo.config;

public class CustomValue {
	
	private Integer value;
	
	public CustomValue(String stringValue) {
		this.value = Integer.valueOf(stringValue);
	}

	public Integer getValue() {
		return value;
	}

}
