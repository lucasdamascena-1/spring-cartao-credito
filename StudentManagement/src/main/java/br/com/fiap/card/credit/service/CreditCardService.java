package br.com.fiap.card.credit.service;

import java.util.List;

import br.com.fiap.card.credit.dto.CreateCreditCardDTO;
import br.com.fiap.card.credit.dto.CreditCardDTO;
import br.com.fiap.card.credit.dto.StudentNameDTO;

public interface CreditCardService {

	List<CreditCardDTO> findAll();

	CreditCardDTO findById(String identity);

	CreditCardDTO create(CreateCreditCardDTO createCreditCardDTO);

	CreditCardDTO update(String identity, StudentNameDTO studentNameDTO);

	void delete(String identity);
}
