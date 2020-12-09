package com.bestseller.challenge.dto;

import com.bestseller.challenge.model.Level;

public interface GameMaxCreditContainer {
	String getGamerId();
	String getGameName();
	Level getGamerLevel();
	int getCredit();
}
