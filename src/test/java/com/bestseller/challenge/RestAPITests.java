package com.bestseller.challenge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.bestseller.challenge.model.Gamer;
import com.bestseller.challenge.repository.GamerInterestsRepository;
import com.bestseller.challenge.repository.GamerRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class RestAPITests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private GamerRepository gamerRepo;

	@Autowired
	private GamerInterestsRepository interestsRepository;

	private ObjectMapper objectMapper = new ObjectMapper();

	@AfterEach
	void clean() {
		interestsRepository.deleteAll();
		gamerRepo.deleteAll();

	}

	@Test
	public void contextLoads() {
	}

	@Test
	void shouldReturn4xx_whenReceiveEmptyGamer() throws Exception {

		String payload = TestHelper.loadJsonFromFile("empty");
		mockMvc.perform(post("/api/gamer").contentType("application/json").content(payload))
				.andExpect(status().is4xxClientError());

	}

	@Test
	void shouldReturn2xx_whenReceiveNormalGamer() throws Exception {

		String payload = TestHelper.loadJsonFromFile("normal");
		mockMvc.perform(post("/api/gamer").contentType("application/json").content(payload))
				.andExpect(status().is2xxSuccessful());

	}

	@Test
	void shouldReturn4xx_whenReceiveDuplicateGamer() throws Exception {

		String payload = TestHelper.loadJsonFromFile("normal");
		mockMvc.perform(post("/api/gamer").contentType("application/json").content(payload))
				.andExpect(status().is2xxSuccessful());

		mockMvc.perform(post("/api/gamer").contentType("application/json").content(payload))
				.andExpect(status().is4xxClientError());

	}

	@Test
	void shouldReturn4xx_whenReceiveUnsopportedLevel() throws Exception {

		String payload = TestHelper.loadJsonFromFile("malformed-level");
		mockMvc.perform(post("/api/gamer").contentType("application/json").content(payload))
				.andExpect(status().is4xxClientError());

	}

	@Test
	void shouldReturn4xx_whenReceiveUnsopportedRegion() throws Exception {

		String payload = TestHelper.loadJsonFromFile("malformed-region");
		mockMvc.perform(post("/api/gamer").contentType("application/json").content(payload))
				.andExpect(status().is4xxClientError());

	}

	@Test
	void shouldReturn4xx_whenReceiveUnsopportedGednder() throws Exception {

		String payload = TestHelper.loadJsonFromFile("malformed-gender");
		mockMvc.perform(post("/api/gamer").contentType("application/json").content(payload))
				.andExpect(status().is4xxClientError());

	}

//	@Test
//	void shouldReturn4xx_whenGiveCreditToNotExistedGamer() throws Exception {
//
//		String payload = TestHelper.loadJsonFromFile("normal");
//		mockMvc.perform(post("/api/gamer").contentType("application/json").content(payload))
//				.andExpect(status().is2xxSuccessful());
//
//		payload = TestHelper.loadJsonFromFile("credit");
//		mockMvc.perform(
//				put("/api/gamer/{gamerId}/credit", "NotAGamer").contentType("application/json").content(payload))
//				.andExpect(status().is4xxClientError());
//
//	}

	@Test
	void shouldReturn4xx_whenGiveNonPositiveCredit() throws Exception {

		String payload = TestHelper.loadJsonFromFile("normal");
		mockMvc.perform(post("/api/gamer").contentType("application/json").content(payload))
				.andExpect(status().is2xxSuccessful());

		payload = TestHelper.loadJsonFromFile("non-positive-credit");
		mockMvc.perform(put("/api/gamer/{gamerId}/credit", "danesh").contentType("application/json").content(payload))
				.andExpect(status().is4xxClientError());

	}

	@Test
	void shouldUpdateCredit_whenGiveCredit() throws Exception {

		String payload = TestHelper.loadJsonFromFile("normal");
		mockMvc.perform(post("/api/gamer").contentType("application/json").content(payload))
				.andExpect(status().is2xxSuccessful());

		payload = TestHelper.loadJsonFromFile("credit");
		mockMvc.perform(put("/api/gamer/{gamerId}/credit", "danesh").contentType("application/json").content(payload))
				.andExpect(status().is2xxSuccessful());

	}

	@Test
	void shouldFindAGamer_whenSearchHasResult() throws Exception {

		String payload = TestHelper.loadJsonFromFile("normal");
		mockMvc.perform(post("/api/gamer").contentType("application/json").content(payload))
				.andExpect(status().is2xxSuccessful());

		payload = TestHelper.loadJsonFromFile("normal2");
		mockMvc.perform(post("/api/gamer").contentType("application/json").content(payload))
				.andExpect(status().is2xxSuccessful());

		String searchParams = TestHelper.loadJsonFromFile("search-by-region-level");
		String result = mockMvc.perform(post("/api/search").contentType("application/json").content(searchParams))
				.andExpect(status().is2xxSuccessful()).andReturn().getResponse().getContentAsString();

		List<Gamer> gamers = objectMapper.readValue(result, new TypeReference<List<Gamer>>() {
		});
		assertThat(gamers.size()).isEqualTo(1);
		Gamer gamer = gamers.get(0);
		assertThat(gamer.getUserId()).isEqualTo("foo");
		
	}
	@Test
	void shouldReturnMaxCreditUserPerGamePerLevel_whenReceiveMaxCreditRequest() throws Exception {

		String payload = TestHelper.loadJsonFromFile("normal");
		mockMvc.perform(post("/api/gamer").contentType("application/json").content(payload))
				.andExpect(status().is2xxSuccessful());

		payload = TestHelper.loadJsonFromFile("normal2");
		mockMvc.perform(post("/api/gamer").contentType("application/json").content(payload))
				.andExpect(status().is2xxSuccessful());
		
		mockMvc.perform(get("/api/credit/max")).andExpect(status().is2xxSuccessful());
		

	}
}
