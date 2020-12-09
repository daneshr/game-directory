package com.bestseller.challenge.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import com.bestseller.challenge.dto.GameMaxCreditContainer;
import com.bestseller.challenge.model.Gamer;
import com.bestseller.challenge.model.GamerInterest;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class GamerDAO {
	private final GamerRepository gamerRepository;
	private final GamerInterestsRepository interestsRepository;
	
	@Transactional
	public void saveGamer(Gamer gamer) {
		
		if(gamerRepository.findOneByUserId(gamer.getUserId()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					String.format("gamer with user id: %s already exists!", gamer.getUserId()));
		}
	
		List<GamerInterest> interests= gamer.getInterests();
		gamerRepository.save(gamer);
		for(GamerInterest gamerInterest:interests) {
			gamerInterest.setGamer(gamer);
			interestsRepository.save(gamerInterest);
		}
	}
	
	@Transactional
	public void replaceInterests(Gamer gamer) {
		Gamer savedGamer = gamerRepository.findOneByUserId(gamer.getUserId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("gamer with user id: %s not found", gamer.getUserId())));
		interestsRepository.deleteAllInterestUser(savedGamer.getId());
		for(GamerInterest gamerInterest:gamer.getInterests()) {
			gamerInterest.setGamer(gamer);
			interestsRepository.save(gamerInterest);
		}
		
	}
	public void changeLevelOfInterest(GamerInterest interest,String userId) {
		Gamer savedGamer = gamerRepository.findOneByUserId(userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("gamer with user id: %s not found", userId)));
		GamerInterest savedInterest = interestsRepository.findOneByNameAndGamer(interest.getName(), savedGamer.getId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("game with name: %s not found for user with user id %s",interest.getName(), userId)));
		savedInterest.setLevel(interest.getLevel());
		interestsRepository.save(savedInterest);
	}
	
	public void giveCredit(String userId, int credit) {
		Gamer savedGamer = gamerRepository.findOneByUserId(userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("gamer with user id: %s not found", userId))); 
		savedGamer.setCredit(credit);
		gamerRepository.save(savedGamer);
	}
	
	public List<GameMaxCreditContainer> getMaxCredits() {
		return interestsRepository.maximumCreditPerGamePerLevel();
	}
	

}

