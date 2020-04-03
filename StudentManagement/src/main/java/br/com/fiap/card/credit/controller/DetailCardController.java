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

import br.com.fiap.card.credit.dto.CreateDetailCardDTO;
import br.com.fiap.card.credit.dto.DeleteDetailCardDTO;
import br.com.fiap.card.credit.dto.DetailCardDTO;
import br.com.fiap.card.credit.service.DetailCardService;

@RestController
@RequestMapping("detail")
public class DetailCardController {

	private final DetailCardService service;

	public DetailCardController(DetailCardService service) {
		this.service = service;
	}

	@GetMapping
	public List<DetailCardDTO> getAll() {
		return service.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DetailCardDTO create(@RequestBody @Valid CreateDetailCardDTO createDetailCardDTO) {
		return service.create(createDetailCardDTO);
	}
	
	@GetMapping(path = "{identification}")
	public List<DetailCardDTO> findAllByStudentId(@PathVariable String identification) {
		return service.findAllByStudentId(identification);
	}
	
	@GetMapping(path = "/download/{identification}")
	public ResponseEntity<Object> downloadFile(@PathVariable String identification) throws IOException {
		return service.downloadFile(identification);
		
	}
	
	@PatchMapping
	public DetailCardDTO update(@RequestBody CreateDetailCardDTO createDetailCardDTO) {
		return service.update(createDetailCardDTO);
	}
	
	@DeleteMapping
	public void delete(@RequestBody DeleteDetailCardDTO deleteDetailCardDTO) {
		service.delete(deleteDetailCardDTO);
	}	
	
}
