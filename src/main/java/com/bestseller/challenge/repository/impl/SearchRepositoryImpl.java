package com.bestseller.challenge.repository.impl;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.bestseller.challenge.dto.SearchParams;
import com.bestseller.challenge.model.Gamer;
import com.bestseller.challenge.repository.GamerRepository;
import com.bestseller.challenge.repository.SearchRepository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class SearchRepositoryImpl implements SearchRepository{
	
	private final GamerRepository gamerRepository;
	
	public List<Gamer> search(SearchParams searchParams){
		return gamerRepository.searchForGamers(
				searchParams.getGame(),
				searchParams.getGamerLevel(),
				searchParams.getGeography(),
				PageRequest.of(0, searchParams.getMaxCount()));
	}
}
