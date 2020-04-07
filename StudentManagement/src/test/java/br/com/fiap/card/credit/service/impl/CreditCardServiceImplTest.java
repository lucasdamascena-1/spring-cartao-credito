package br.com.fiap.card.credit.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.card.credit.dto.CreditCardDTO;
import br.com.fiap.card.credit.entity.CreditCard;
import br.com.fiap.card.credit.repository.CreditCardRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CreditCardServiceImplTest {

	@Autowired
	private CreditCardRepository creditCardRespository;

	@Test
	public void findAll() {
		creditCardRespository.findAll();
	}

	@Test
	public void findStudentById() {
		creditCardRespository.findById("2438191 790-46");
	}

	@Test
	@Before
	public void insertCreditCard() {
		CreditCardDTO createCreditCardDTO = instantiateCreditCardDTO("1234567 890-88", "Celso Holanda");
		CreditCard creditCard = new CreditCard(createCreditCardDTO);
		saveAndGetCreditCardDTO(creditCard);
	}

	@Test
	public void updateCreditCard() {
		String identification = "3345594 710-13";
		String newName = "Jesualdo Ferreira";
		CreditCard creditCard = getCreditCard(identification);
		creditCard.setStudentName(newName);
		saveAndGetCreditCardDTO(creditCard);
	}

	@Test
	public void deleteCreditCard() {
		CreditCard creditCard = getCreditCard("1234567 890-88");
		creditCardRespository.delete(creditCard);
	}

	private CreditCard getCreditCard(String identification) {
		return creditCardRespository.findById(identification)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	private CreditCardDTO instantiateCreditCardDTO(String identification, String studentName) {
		CreditCardDTO createCreditCardDTO = new CreditCardDTO();
		createCreditCardDTO.setIdentification(identification);
		createCreditCardDTO.setStudentName(studentName);

		return createCreditCardDTO;
	}

	private CreditCardDTO saveAndGetCreditCardDTO(CreditCard creditCard) {
		CreditCard savedCreditCard = creditCardRespository.save(creditCard);
		return new CreditCardDTO(savedCreditCard);
	}
}
