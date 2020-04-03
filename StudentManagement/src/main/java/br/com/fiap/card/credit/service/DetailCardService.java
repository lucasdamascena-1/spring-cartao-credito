package br.com.fiap.card.credit.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.fiap.card.credit.dto.CreateDetailCardDTO;
import br.com.fiap.card.credit.dto.DeleteDetailCardDTO;
import br.com.fiap.card.credit.dto.DetailCardDTO;

public interface DetailCardService {
	
	DetailCardDTO create(CreateDetailCardDTO createDetailCardDTO);
	List<DetailCardDTO> findAll();
	List<DetailCardDTO> findAllByStudentId(String studentId);
	ResponseEntity<Object> downloadFile(String id) throws IOException;
	DetailCardDTO update(CreateDetailCardDTO createDetailCardDTO);
	void delete(DeleteDetailCardDTO deleteDetailCardDTO);

}
