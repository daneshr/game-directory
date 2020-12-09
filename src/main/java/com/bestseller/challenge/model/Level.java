package com.bestseller.challenge.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Level {
	NOOB, PRO, INVINCIBLE;

	@JsonCreator
	public static Level forValue(String value) {
		return Level.valueOf(value.toUpperCase());

	}

}
