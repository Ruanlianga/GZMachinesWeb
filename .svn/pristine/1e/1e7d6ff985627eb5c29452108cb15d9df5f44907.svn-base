package com.bonus.core.json;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JacksonHelper implements JsonHelper {

	@Override
	public String jsonSerialize(Object value) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(value);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T jsonDeserialize(String value, Class<?> tClass) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return (T) objectMapper.readValue(value, tClass);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T jsonDeserialize(String value,
			TypeReference<T> typeReference) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return (T) objectMapper.readValue(value, typeReference);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
