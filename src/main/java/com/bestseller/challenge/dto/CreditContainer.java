package com.bestseller.challenge.dto;

import javax.validation.constraints.Min;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditContainer {
	
	@NonNull
	@Min(1)
	private Integer credit;
}
