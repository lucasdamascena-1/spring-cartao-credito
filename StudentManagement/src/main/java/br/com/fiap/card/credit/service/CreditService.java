package br.com.fiap.card.credit.service;

import java.util.List;

import br.com.fiap.card.credit.dto.CreateStudentDTO;
import br.com.fiap.card.credit.dto.StudentDTO;
import br.com.fiap.card.credit.dto.StudentNameDTO;

public interface CreditService {
	
	List<StudentDTO> findAll(String name);

	StudentDTO findById(String Identity);

	StudentDTO create(CreateStudentDTO createStudentDTO);

	StudentDTO update(String identity, StudentNameDTO studentNameDTO);

	void delete(String identity);
}
