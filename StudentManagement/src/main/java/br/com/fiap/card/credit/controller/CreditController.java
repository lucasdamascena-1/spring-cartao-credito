package br.com.fiap.card.credit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.card.credit.dto.CreateStudentDTO;
import br.com.fiap.card.credit.dto.StudentDTO;
import br.com.fiap.card.credit.dto.StudentNameDTO;

@RestController
@RequestMapping("students")
public class CreditController {

	private List<StudentDTO> studentList;

	public CreditController() {

		studentList = new ArrayList<>();

		studentList.add(new StudentDTO("3095564100-11", "AARON FELIPE GRASSMANN"));

		studentList.add(new StudentDTO("8610833160-26", "AARON PAPA DE MORAIS"));

		studentList.add(new StudentDTO("1494778500-35", "ABNER GALLILEI MOREIRA BORGES"));

		studentList.add(new StudentDTO("1209154500-34", "BRUNO DEYVID ALVES DE LIMA BARRETO"));

	}

	@GetMapping
	public List<StudentDTO> getAll(@RequestParam(required = false) String name) {
		return studentList.stream()
				.filter(studentDTO -> name == null || studentDTO.getName().startsWith(name.toUpperCase()))
				.collect(Collectors.toList());
	}

	@GetMapping("{identity}")
	public StudentDTO findById(@PathVariable String identity) {

		return studentList.stream().filter(studentDTO -> studentDTO.getIdentity().equals(identity)).findFirst()
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public StudentDTO create(@RequestBody @Valid CreateStudentDTO createStudentDTO) {
		StudentDTO studentDTO = new StudentDTO(createStudentDTO);
		studentList.add(studentDTO);
		return studentDTO;
	}

	@PatchMapping("{identity}")
	public StudentDTO update(@PathVariable String identity, @RequestBody StudentNameDTO studentIdentityDTO) {
		StudentDTO studentDTO = findById(identity);
		studentDTO.setName(studentIdentityDTO.getName());
		return studentDTO;
	}

	@DeleteMapping("{identity}")
	public void delete(@PathVariable String identity) {
		StudentDTO studentDTO = findById(identity);
		studentList.remove(studentDTO);
	}
}
