package br.com.fiap.card.credit.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;

import br.com.fiap.card.credit.dto.CreateCreditCardDetailsDTO;
import br.com.fiap.card.credit.dto.CreditCardDetailsDTO;
import br.com.fiap.card.credit.generic.AbstractTest;

public class CreditCardDetailsControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void returnAllCreditCardDetails() throws Exception {
		String uri = "/details/";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		CreditCardDetailsDTO[] arrCreditCardDetailsDTO = super.mapFromJson(content, CreditCardDetailsDTO[].class);

		assertTrue(arrCreditCardDetailsDTO.length > 0);
	}

	@Test
	public void returnFindAllDataByCreditCardId() throws Exception {
		String uri = "/details/8610833 160-26";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		CreditCardDetailsDTO[] arrCreditCardDetailsDTO = super.mapFromJson(content, CreditCardDetailsDTO[].class);

		assertTrue(arrCreditCardDetailsDTO.length > 0);
	}

	@Test
	public void createCreditCardDetails() throws Exception {
		String uri = "/details/";

		CreateCreditCardDetailsDTO createCreditCardDetailsDTO = new CreateCreditCardDetailsDTO();
		createCreditCardDetailsDTO.setDateOperation("10/04/2020");
		createCreditCardDetailsDTO.setDescriptionOperation("Compra");
		createCreditCardDetailsDTO.setStudentId("8610833 160-26");
		createCreditCardDetailsDTO.setTypeOperation('B');
		createCreditCardDetailsDTO.setValueOperation(new BigDecimal(3513));

		String inputJson = super.mapToJson(createCreditCardDetailsDTO);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}

	@Test
	public void updateCreditCardDetails() throws Exception {
		String uri = "/details/";

		CreateCreditCardDetailsDTO objCreditCardDetailsDTO = new CreateCreditCardDetailsDTO();
		objCreditCardDetailsDTO.setDateOperation("15/04/2020");
		objCreditCardDetailsDTO.setDescriptionOperation("COMPRA PARCELADA");
		objCreditCardDetailsDTO.setOperationId(5);
		objCreditCardDetailsDTO.setStudentId("8610833 160-26");
		objCreditCardDetailsDTO.setTypeOperation('B');
		objCreditCardDetailsDTO.setValueOperation(new BigDecimal(1500.00));

		Gson gson = new Gson();
		String result = gson.toJson(objCreditCardDetailsDTO);

		MvcResult mvcResult = mvc
				.perform(
						MockMvcRequestBuilders.patch(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(result))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void deleteCreditCardDetails() throws Exception {
		String uri = "/details/";

		CreateCreditCardDetailsDTO objCreditCardDetailsDTO = new CreateCreditCardDetailsDTO();
		objCreditCardDetailsDTO.setDateOperation("08/04/2020");
		objCreditCardDetailsDTO.setDescriptionOperation("COMPRA");
		objCreditCardDetailsDTO.setOperationId(4);
		objCreditCardDetailsDTO.setStudentId("3095564 100-11");
		objCreditCardDetailsDTO.setTypeOperation('B');
		objCreditCardDetailsDTO.setValueOperation(new BigDecimal(4000.00));

		Gson gson = new Gson();
		String result = gson.toJson(objCreditCardDetailsDTO);

		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(result))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
}
