package com.bestseller.challenge.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bestseller.challenge.model.Gamer;
import com.bestseller.challenge.model.GamerInterest;
import com.bestseller.challenge.repository.GamerDAO;
import com.bestseller.challenge.service.GamerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GamerServiceImpl implements GamerService {
	
	private final GamerDAO gamerDao;

	@Override
	public void addNewGamerGeneralInfo(Gamer gamer) {
		if(gamer.getInterests().size()>5) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					String.format("number of interests should be less than 5 now it is:%d !", gamer.getInterests().size()));
		}
		gamerDao.saveGamer(gamer);
		
	}

	@Override
	public void addOrReplaceInterests(Gamer gamer) {
		gamerDao.replaceInterests(gamer);
		
	}

	@Override
	public void changeLevelOfInterest(GamerInterest interest, String userId) {
		gamerDao.changeLevelOfInterest(interest, userId);
		
	}

}
