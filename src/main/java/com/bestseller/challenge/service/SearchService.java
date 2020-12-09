package com.bestseller.challenge.service;

import java.util.List;

import com.bestseller.challenge.dto.SearchParams;
import com.bestseller.challenge.model.Gamer;

public interface SearchService {
	
	List<Gamer> search(SearchParams params);

}
