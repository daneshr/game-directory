package com.bestseller.challenge.repository;

import java.util.List;

import com.bestseller.challenge.dto.SearchParams;
import com.bestseller.challenge.model.Gamer;

public interface SearchRepository {
	public List<Gamer> search(SearchParams searchParams);
}
