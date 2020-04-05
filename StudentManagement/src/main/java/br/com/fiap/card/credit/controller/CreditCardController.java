package br.com.fiap.card.credit.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.fiap.card.credit.dto.CreditCardDTO;
import br.com.fiap.card.credit.service.CreditCardService;

@RestController
@RequestMapping("students")
public class CreditCardController {

	private final CreditCardService service;

	public CreditCardController(CreditCardService service) {
		this.service = service;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CreditCardDTO> getAll() {
		return service.findAll();
	}

	@GetMapping("{identification}")
	@ResponseStatus(HttpStatus.OK)
	public CreditCardDTO findById(@PathVariable String identification) {
		return service.findById(identification);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CreditCardDTO create(@RequestBody @Valid CreditCardDTO createCreditCardDTO) {
		return service.create(createCreditCardDTO);
	}

	@PatchMapping("{identification}")
	@ResponseStatus(HttpStatus.OK)
	public CreditCardDTO update(@PathVariable String identification,
			@RequestBody com.fasterxml.jackson.databind.JsonNode value) {

		JsonNode studentName = value.get("studentName");
		return service.update(identification, studentName.textValue());
	}

	@DeleteMapping("{identification}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable String identification) {
		service.delete(identification);
	}
}
