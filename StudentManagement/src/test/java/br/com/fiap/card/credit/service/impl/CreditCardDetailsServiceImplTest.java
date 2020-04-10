package br.com.fiap.card.credit.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.fiap.card.credit.dto.CreateCreditCardDetailsDTO;
import br.com.fiap.card.credit.dto.CreditCardDetailsDTO;
import br.com.fiap.card.credit.entity.CreditCardDetails;
import br.com.fiap.card.credit.repository.CreditCardDetailsRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CreditCardDetailsServiceImplTest {
	
	@Autowired
	private CreditCardDetailsRepository detailCardRepository;
	
	@Test
	public void create() {	
		CreateCreditCardDetailsDTO objCreditCardDetailsDTO = new CreateCreditCardDetailsDTO();
		objCreditCardDetailsDTO.setDateOperation("08/04/2020");
		objCreditCardDetailsDTO.setDescriptionOperation("COMPRA");
		objCreditCardDetailsDTO.setOperationId(4);
		objCreditCardDetailsDTO.setStudentId("3095564 200-33");
		objCreditCardDetailsDTO.setTypeOperation('B');
		objCreditCardDetailsDTO.setValueOperation(new BigDecimal(4000.00));
		
		CreditCardDetails detailCard = new CreditCardDetails(objCreditCardDetailsDTO);
		detailCardRepository.save(detailCard);
			
	}

	/*public CreditCardDetailsDTO saveAndGetDetailCardDTO(CreditCardDetails detailCard) {
		CreditCardDetails savedDetailCard = detailCardRepository.save(detailCard);
		return new CreditCardDetailsDTO(savedDetailCard);
	}*/

	@Test
	public void findAll() {
		detailCardRepository.findAll().stream().map(CreditCardDetailsDTO::new).collect(Collectors.toList());
	}

	@Test
	public void findAllByCreditCardDetailsId() {
		List<CreditCardDetails> detailCardList;
		detailCardList = detailCardRepository.findByIdStudentId("1494778 500-35");
		detailCardList.stream().map(CreditCardDetailsDTO::new).collect(Collectors.toList());
	}


	@Test
	public void update() {
		CreateCreditCardDetailsDTO objCreditCardDetailsDTO = new CreateCreditCardDetailsDTO();
		objCreditCardDetailsDTO.setDateOperation("08/04/2020");
		objCreditCardDetailsDTO.setDescriptionOperation("COMPRA");
		objCreditCardDetailsDTO.setOperationId(4);
		objCreditCardDetailsDTO.setStudentId("3095564 100-11");
		objCreditCardDetailsDTO.setTypeOperation('B');
		objCreditCardDetailsDTO.setValueOperation(new BigDecimal(4000.00));
		
		CreditCardDetails detailCard = new CreditCardDetails(objCreditCardDetailsDTO);
		detailCardRepository.save(detailCard);
	}

	@Test
	public void delete() {
		CreateCreditCardDetailsDTO objCreditCardDetailsDTO = new CreateCreditCardDetailsDTO();
		objCreditCardDetailsDTO.setDateOperation("08/04/2020");
		objCreditCardDetailsDTO.setDescriptionOperation("COMPRA");
		objCreditCardDetailsDTO.setOperationId(4);
		objCreditCardDetailsDTO.setStudentId("3095564 100-11");
		objCreditCardDetailsDTO.setTypeOperation('B');
		objCreditCardDetailsDTO.setValueOperation(new BigDecimal(4000.00));
		
		detailCardRepository.deleteByIdOperationIdAndIdStudentId(objCreditCardDetailsDTO.getOperationId(),
				objCreditCardDetailsDTO.getStudentId());
	}
}
