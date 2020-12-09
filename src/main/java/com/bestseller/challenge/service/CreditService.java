package com.bestseller.challenge.service;

import java.util.List;

import com.bestseller.challenge.dto.GameMaxCreditContainer;

public interface CreditService {

	void giveCredit(String gamerId, int credit);

	List<GameMaxCreditContainer> getMaximumCredits();
}
