package br.com.fiap.card.credit.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.card.credit.dto.CreateCreditCardDTO;
import br.com.fiap.card.credit.dto.CreditCardDTO;
import br.com.fiap.card.credit.dto.StudentNameDTO;
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
	public List<CreditCardDTO> findAll(String name) {

		List<CreditCard> cardList = new ArrayList<CreditCard>();

		if (name == null) {
			cardList = creditCardRepository.findAll();
		} /*else {
			cardList = creditCardRepository.findAllByInitial(name);
		}*/

		return cardList.stream().map(CreditCardDTO::new).collect(Collectors.toList());
	}

	@Override
	public CreditCardDTO findById(String identification) {
		return saveAndGetCreditCardDTO(getCreditCard(identification));
	}

	private CreditCard getCreditCard(String identification) {
		return creditCardRepository.findById(identification)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@Override
	public CreditCardDTO create(CreateCreditCardDTO createCreditCardDTO) {
		CreditCard creditCard = new CreditCard(createCreditCardDTO);
		return saveAndGetCreditCardDTO(creditCard);
	}

	private CreditCardDTO saveAndGetCreditCardDTO(CreditCard creditCard) {
		CreditCard savedCreditCard = creditCardRepository.save(creditCard);
		return new CreditCardDTO(savedCreditCard);
	}

	@Override
	public CreditCardDTO update(String identification, StudentNameDTO studentNameDTO) {
		CreditCard creditCard = getCreditCard(identification);
		creditCard.setStudentName(studentNameDTO.getName());

		return saveAndGetCreditCardDTO(creditCard);
	}

	@Override
	public void delete(String identification) {
		CreditCard creditCard = getCreditCard(identification);
		creditCardRepository.delete(creditCard);
	}
}
