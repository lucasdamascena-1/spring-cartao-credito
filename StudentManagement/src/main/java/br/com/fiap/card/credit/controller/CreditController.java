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

import br.com.fiap.card.credit.dto.CreateStudentDTO;
import br.com.fiap.card.credit.dto.StudentDTO;
import br.com.fiap.card.credit.dto.StudentNameDTO;
import br.com.fiap.card.credit.service.CreditService;

@RestController
@RequestMapping("students")
public class CreditController {

	private final CreditService service;

	public CreditController(CreditService service) {
		this.service = service;
	}

	@GetMapping
	public List<StudentDTO> getAll(@RequestParam(required = false) String name) {
		return service.findAll(name);
	}

	@GetMapping("{identity}")
	public StudentDTO findById(@PathVariable String identity) {
		return findById(identity);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public StudentDTO create(@RequestBody @Valid CreateStudentDTO createStudentDTO) {
		return service.create(createStudentDTO);
	}

	@PatchMapping("{identity}")
	public StudentDTO update(@PathVariable String identity, @RequestBody StudentNameDTO studentNameDTO) {
		return service.update(identity, studentNameDTO);
	}

	@DeleteMapping("{identity}")
	public void delete(@PathVariable String identity) {
		service.delete(identity);
	}
}
