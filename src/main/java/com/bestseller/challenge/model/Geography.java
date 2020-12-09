package com.bestseller.challenge.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Geography {
	EUROPE, ASIA , USA;
	
	@JsonCreator
	public static Geography forValue(String value) {
		return Geography.valueOf(value.toUpperCase());
	}
}
