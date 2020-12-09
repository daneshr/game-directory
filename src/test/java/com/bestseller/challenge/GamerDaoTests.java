package com.bestseller.challenge;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bestseller.challenge.model.Gamer;
import com.bestseller.challenge.repository.GamerDAO;
import com.bestseller.challenge.repository.GamerInterestsRepository;
import com.bestseller.challenge.repository.GamerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@ExtendWith(MockitoExtension.class)
public class GamerDaoTests {
	
	@Mock
	private GamerRepository gamerRepository;
	
	@Mock
	private GamerInterestsRepository interestsRepository;


	@InjectMocks
	private GamerDAO gameDao;

	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	void shouldThrowException_whenReceiveExistedUser() throws IOException {
		String gamerString = TestHelper.loadJsonFromFile("normal");
		Gamer gamer = objectMapper.readValue(gamerString, Gamer.class);
		when(gamerRepository.findOneByUserId(gamer.getUserId())).thenReturn(Optional.of(gamer));
		Assertions.assertThrows(RuntimeException.class, () -> gameDao.saveGamer(gamer));

	}
	@Test
	void shouldSaveGamer_whenReceiveNewGamer() throws IOException {
		String gamerString = TestHelper.loadJsonFromFile("normal");
		Gamer gamer = objectMapper.readValue(gamerString, Gamer.class);
		
		when(gamerRepository.findOneByUserId(gamer.getUserId())).thenReturn(Optional.empty());
		gameDao.saveGamer(gamer);
		verify(gamerRepository).save(gamer);
		verify(interestsRepository,times(5)).save(Mockito.any());

	}
}
