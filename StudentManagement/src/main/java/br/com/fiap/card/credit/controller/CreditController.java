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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.card.credit.dto.CreateCreditCardDTO;
import br.com.fiap.card.credit.dto.CreditCardDTO;
import br.com.fiap.card.credit.dto.StudentNameDTO;
import br.com.fiap.card.credit.service.CreditCardService;

@RestController
@RequestMapping("students")
public class CreditController {

	private final CreditCardService service;

	public CreditController(CreditCardService service) {
		this.service = service;
	}

	@GetMapping
	public List<CreditCardDTO> getAll(@RequestParam(required = false) String name) {
		return service.findAll(name);
	}

	@GetMapping("{identity}")
	public CreditCardDTO findById(@PathVariable String identity) {
		return findById(identity);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CreditCardDTO create(@RequestBody @Valid CreateCreditCardDTO createCreditCardDTO) {
		return service.create(createCreditCardDTO);
	}

	@PatchMapping("{identity}")
	public CreditCardDTO update(@PathVariable String identity, @RequestBody StudentNameDTO studentNameDTO) {
		return service.update(identity, studentNameDTO);
	}

	@DeleteMapping("{identity}")
	public void delete(@PathVariable String identity) {
		service.delete(identity);
	}
}
