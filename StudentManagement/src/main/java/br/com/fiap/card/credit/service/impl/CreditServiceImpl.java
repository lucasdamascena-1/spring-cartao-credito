package br.com.fiap.card.credit.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.card.credit.dto.CreditCardDTO;
import br.com.fiap.card.credit.entity.CreditCard;
import br.com.fiap.card.credit.repository.CreditCardRepository;
import br.com.fiap.card.credit.service.CreditCardService;

@Service
public class CreditServiceImpl implements CreditCardService {

	private CreditCardRepository creditCardRepository;

	public CreditServiceImpl(CreditCardRepository creditCardRepository) {
		this.creditCardRepository = creditCardRepository;
	}

	@Override
	public List<CreditCardDTO> findAll() {
		return creditCardRepository.findAll().stream().map(CreditCardDTO::new).collect(Collectors.toList());
	}

	@Override
	public CreditCardDTO findById(String identification) {
		return saveAndGetCreditCardDTO(getCreditCard(identification));
	}

	@Override
	public CreditCardDTO create(CreditCardDTO createCreditCardDTO) {
		CreditCard creditCard = new CreditCard(createCreditCardDTO);
		return saveAndGetCreditCardDTO(creditCard);
	}

	@Override
	public CreditCardDTO update(String identification, String studentName) {
		CreditCard creditCard = getCreditCard(identification);
		creditCard.setStudentName(studentName);
		return saveAndGetCreditCardDTO(creditCard);
	}

	@Override
	public void delete(String identification) {
		CreditCard creditCard = getCreditCard(identification);
		creditCardRepository.delete(creditCard);
	}

	private CreditCard getCreditCard(String identification) {
		return creditCardRepository.findById(identification)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	private CreditCardDTO saveAndGetCreditCardDTO(CreditCard creditCard) {
		CreditCard savedCreditCard = creditCardRepository.save(creditCard);
		return new CreditCardDTO(savedCreditCard);
	}
}