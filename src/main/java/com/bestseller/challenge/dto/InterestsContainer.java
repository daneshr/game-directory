package com.bestseller.challenge.dto;

import java.util.List;

import com.bestseller.challenge.model.GamerInterest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InterestsContainer {
	
	private String gamerId;
	private List<GamerInterest> interests;

}
