package br.com.fiap.card.credit.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.fiap.card.credit.dto.CreateCreditCardDetailsDTO;
import br.com.fiap.card.credit.dto.DeleteCreditCardDetailsDTO;
import br.com.fiap.card.credit.dto.CreditCardDetailsDTO;

public interface CreditCardDetailsService {

	CreditCardDetailsDTO create(CreateCreditCardDetailsDTO createDetailCardDTO);

	List<CreditCardDetailsDTO> findAll();

	List<CreditCardDetailsDTO> findAllByStudentId(String studentId);

	ResponseEntity<Object> downloadFile(String id) throws IOException;

	CreditCardDetailsDTO update(CreateCreditCardDetailsDTO createDetailCardDTO);

	void delete(DeleteCreditCardDetailsDTO deleteDetailCardDTO);
}
