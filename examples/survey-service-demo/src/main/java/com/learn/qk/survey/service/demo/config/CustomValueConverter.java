package com.learn.qk.survey.service.demo.config;

import org.eclipse.microprofile.config.spi.Converter;

public class CustomValueConverter implements Converter<CustomValue> {

	private static final long serialVersionUID = 1L;

	@Override
	public CustomValue convert(String value) throws IllegalArgumentException, NullPointerException {
		return new CustomValue(value);
	}

}
