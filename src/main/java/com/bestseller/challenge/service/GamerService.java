package com.bestseller.challenge.service;

import com.bestseller.challenge.model.Gamer;
import com.bestseller.challenge.model.GamerInterest;

public interface GamerService {
	
	void addNewGamerGeneralInfo(Gamer gamer);
	
	void addOrReplaceInterests(Gamer gamer);
	
	void changeLevelOfInterest(GamerInterest interest,String userId);

}
