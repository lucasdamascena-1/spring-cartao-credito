package br.com.fiap.card.credit.controller;

import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.card.credit.dto.CreateCreditCardDetailsDTO;
import br.com.fiap.card.credit.dto.DeleteCreditCardDetailsDTO;
import br.com.fiap.card.credit.dto.CreditCardDetailsDTO;
import br.com.fiap.card.credit.service.CreditCardDetailsService;

@RestController
@RequestMapping("details")
public class CreditCardDetailsController {

	private final CreditCardDetailsService service;

	public CreditCardDetailsController(CreditCardDetailsService service) {
		this.service = service;
	}

	@GetMapping
	public List<CreditCardDetailsDTO> getAll() {
		return service.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CreditCardDetailsDTO create(@RequestBody @Valid CreateCreditCardDetailsDTO createDetailCardDTO) {
		return service.create(createDetailCardDTO);
	}

	@GetMapping(path = "{identification}")
	public List<CreditCardDetailsDTO> findAllByStudentId(@PathVariable String identification) {
		return service.findAllByStudentId(identification);
	}

	@GetMapping(path = "/download/{identification}")
	public ResponseEntity<Object> downloadFile(@PathVariable String identification) throws IOException {
		return service.downloadFile(identification);

	}

	@PatchMapping
	public CreditCardDetailsDTO update(@RequestBody CreateCreditCardDetailsDTO createDetailCardDTO) {
		return service.update(createDetailCardDTO);
	}

	@DeleteMapping
	public void delete(@RequestBody DeleteCreditCardDetailsDTO deleteDetailCardDTO) {
		service.delete(deleteDetailCardDTO);
	}

}
