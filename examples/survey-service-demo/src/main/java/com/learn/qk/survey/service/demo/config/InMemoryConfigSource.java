package com.learn.qk.survey.service.demo.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.microprofile.config.spi.ConfigSource;

import io.quarkus.runtime.annotations.StaticInitSafe;

@StaticInitSafe
public class InMemoryConfigSource implements ConfigSource {
	
	private static final Map<String, String> configuration = new HashMap<>();
	
	static {
		configuration.put("my.custom-prop", "1234");
		configuration.put("my.custom-value", "2500");
	}

	@Override
	public Set<String> getPropertyNames() {
		return configuration.keySet();
	}

	@Override
	public String getValue(String propertyName) {
		return configuration.get(propertyName);
	}

	@Override
	public String getName() {
		return InMemoryConfigSource.class.getSimpleName();
	}
	
	@Override
	public int getOrdinal() {
		return 275;
	}

}
