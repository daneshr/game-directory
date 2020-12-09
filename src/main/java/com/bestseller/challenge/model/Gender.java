package com.bestseller.challenge.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Gender {
	FEMALE, MALE , OTHER;
	
	@JsonCreator
	public static Gender forValue(String value) {
		return Gender.valueOf(value.toUpperCase());
	}
}
