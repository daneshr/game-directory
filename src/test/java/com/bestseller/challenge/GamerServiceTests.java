package com.bestseller.challenge;

import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bestseller.challenge.model.Gamer;
import com.bestseller.challenge.repository.GamerDAO;
import com.bestseller.challenge.service.impl.GamerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class GamerServiceTests {

	@Mock
	GamerDAO gamerDao;

	@InjectMocks
	GamerServiceImpl gamerService;

	@InjectMocks
	private ObjectMapper objectMapper;

	@Test
	void shouldSaveUser_whenReceiveNormalGamer() throws IOException {
		Gamer gamer = objectMapper.readValue(TestHelper.loadJsonFromFile("normal"), Gamer.class);
		gamerService.addNewGamerGeneralInfo(gamer);
		verify(gamerDao).saveGamer(gamer);
	
	}

}
