package br.com.fiap.card.credit.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.card.credit.dto.CreateStudentDTO;
import br.com.fiap.card.credit.dto.StudentDTO;
import br.com.fiap.card.credit.dto.StudentNameDTO;
import br.com.fiap.card.credit.service.CreditService;

public class CreditServiceImpl implements CreditService {

	private List<StudentDTO> studentList;

	public CreditServiceImpl() {

		studentList = new ArrayList<>();

		studentList.add(new StudentDTO("3095564100-11", "AARON FELIPE GRASSMANN"));

		studentList.add(new StudentDTO("8610833160-26", "AARON PAPA DE MORAIS"));

		studentList.add(new StudentDTO("1494778500-35", "ABNER GALLILEI MOREIRA BORGES"));

		studentList.add(new StudentDTO("1209154500-34", "BRUNO DEYVID ALVES DE LIMA BARRETO"));
	}

	@Override
	public List<StudentDTO> findAll(String name) {
		return studentList.stream()
				.filter(studentDTO -> name == null || studentDTO.getName().startsWith(name.toUpperCase()))
				.collect(Collectors.toList());
	}

	@Override
	public StudentDTO findById(String identity) {
		return studentList.stream().filter(studentDTO -> studentDTO.getIdentity().equals(identity)).findFirst()
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@Override
	public StudentDTO create(CreateStudentDTO createStudentDTO) {
		StudentDTO studentDTO = new StudentDTO(createStudentDTO);
		studentList.add(studentDTO);
		return studentDTO;
	}

	@Override
	public StudentDTO update(String identity, StudentNameDTO studentNameDTO) {
		StudentDTO studentDTO = findById(identity);
		studentDTO.setName(studentNameDTO.getName());
		return studentDTO;
	}

	@Override
	public void delete(String identity) {
		StudentDTO studentDTO = findById(identity);
		studentList.remove(studentDTO);
	}

}
