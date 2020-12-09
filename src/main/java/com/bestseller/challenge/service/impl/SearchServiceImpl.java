package com.bestseller.challenge.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bestseller.challenge.dto.SearchParams;
import com.bestseller.challenge.model.Gamer;
import com.bestseller.challenge.repository.SearchRepository;
import com.bestseller.challenge.service.SearchService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

	private final SearchRepository searchRepo;

	@Override
	public List<Gamer> search(SearchParams params) {

		return searchRepo.search(params);
	}

}
