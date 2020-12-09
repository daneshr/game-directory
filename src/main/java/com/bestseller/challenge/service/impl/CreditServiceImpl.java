package com.bestseller.challenge.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bestseller.challenge.dto.GameMaxCreditContainer;
import com.bestseller.challenge.repository.GamerDAO;
import com.bestseller.challenge.service.CreditService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CreditServiceImpl implements CreditService {
	
	private final GamerDAO gamerDAO;


	@Override
	public List<GameMaxCreditContainer> getMaximumCredits() {
		return gamerDAO.getMaxCredits();
	}

	@Override
	public void giveCredit(String gamerId, int credit) {
		gamerDAO.giveCredit(gamerId, credit);
		
	}

}
