package br.com.fiap.card.credit.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.fiap.card.credit.dto.CreateCreditCardDTO;
import br.com.fiap.card.credit.dto.TransactionCardDTO;

public interface TransactionCardService {
	
	TransactionCardDTO findById(int id);
	TransactionCardDTO create(CreateCreditCardDTO createDetailCardDTO);
	List<TransactionCardDTO> findAll();
	List<TransactionCardDTO> findAllByStudentId(String studentId);
	ResponseEntity<Object> downloadFile(String id) throws IOException;

}
