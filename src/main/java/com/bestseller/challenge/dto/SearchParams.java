package com.bestseller.challenge.dto;

import javax.validation.constraints.NotBlank;

import com.bestseller.challenge.model.Geography;
import com.bestseller.challenge.model.Level;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchParams {

	public static final int DEFAULT_MAX_RESULT = 10;

	@NotBlank
	private String game;
	private Level gamerLevel;
	private Geography geography;
	private int maxCount = DEFAULT_MAX_RESULT;

}
