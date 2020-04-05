package br.com.fiap.card.credit.service;

import java.util.List;

import br.com.fiap.card.credit.dto.CreditCardDTO;

public interface CreditCardService {

	List<CreditCardDTO> findAll();

	CreditCardDTO findById(String identity);

	CreditCardDTO create(CreditCardDTO createCreditCardDTO);

	CreditCardDTO update(String identity, String studentName);

	void delete(String identity);
}
