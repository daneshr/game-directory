package com.bestseller.challenge.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bestseller.challenge.dto.CreditContainer;
import com.bestseller.challenge.dto.ErrorMessageContainer;
import com.bestseller.challenge.dto.GameMaxCreditContainer;
import com.bestseller.challenge.dto.SearchParams;
import com.bestseller.challenge.model.Gamer;
import com.bestseller.challenge.service.CreditService;
import com.bestseller.challenge.service.GamerService;
import com.bestseller.challenge.service.SearchService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Log4j2
public class GamerController {

	private final GamerService gamerService;
	private final CreditService creditService;
	private final SearchService searchService;

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<ErrorMessageContainer> handleException(ResponseStatusException ex) {
		log.warn(String.format("error : %s", ex.getMessage()));
		return ResponseEntity.status(ex.getStatus())
				.body(ErrorMessageContainer.builder().errorMessage(ex.getReason()).build());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorMessageContainer> handleException(HttpMessageNotReadableException ex) {
		log.warn(String.format("error : %s", ex.getMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ErrorMessageContainer.builder().errorMessage("not supported value ").build());
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorMessageContainer> handleGenericExceptions(RuntimeException ex) {
		log.warn(String.format("error : %s", ex.getMessage()));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ErrorMessageContainer.builder().errorMessage(ex.getMessage()).build());
	}

	@ApiOperation(value = "Add Gamer General Info")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Operation was successful."),
			@ApiResponse(code = 400, message = "Gamer id duplication"),
			@ApiResponse(code = 400, message = "Gamer has more than 5 interests"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping(value = "/gamer")
	public ResponseEntity<String> addNewGamerGeneralInfo(
			@Valid @RequestBody @ApiParam(value = "Gamer general info") Gamer gamerGeneralInfo) {
		log.info(String.format("add gamer with user Id: %s", gamerGeneralInfo.getUserId()));
		gamerService.addNewGamerGeneralInfo(gamerGeneralInfo);
		return ResponseEntity.accepted().body("Succeed!");
	}

	@ApiOperation(value = "Update Gamer Credit")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Operation was successful."),
			@ApiResponse(code = 400, message = "Gamer id duplication"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PutMapping(value = "/gamer/{gamerId}/credit")
	public ResponseEntity<String> updateGamerCredit(@PathVariable @ApiParam(value = "Gamer Id") String gamerId,
			@Valid @RequestBody @ApiParam(value = "New Credit") CreditContainer gamerCredit) {
		log.info(String.format("update credit to %d for gamer with id:%s", gamerCredit.getCredit(), gamerId));
		creditService.giveCredit(gamerId, gamerCredit.getCredit());
		return ResponseEntity.accepted().body("Gamer Credit updated!");
	}

	@ApiOperation(value = "Retrieve Max Credit Per Game Per Level")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Operation was successful.") })
	@GetMapping(value = "/credit/max")
	public ResponseEntity<List<GameMaxCreditContainer>> getMaxCreditPerGamePerLevel() {
		log.info(String.format("max credit per game per level is called"));
		List<GameMaxCreditContainer> result = creditService.getMaximumCredits();
		return ResponseEntity.accepted().body(result);
	}
	@ApiOperation(value = "Search with game name, optional level and optional region parameters")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Operation was successful."),
			@ApiResponse(code = 400, message = "Game name not found") })
	@PostMapping(value = "/search")
	public ResponseEntity<List<Gamer>> getMaxCreditPerGamePerLevel(@RequestBody @Valid SearchParams searchParams) {
		log.info(String.format("search for: %s ",searchParams));
		List<Gamer> result = searchService.search(searchParams);
		return ResponseEntity.accepted().body(result);
	}

}
