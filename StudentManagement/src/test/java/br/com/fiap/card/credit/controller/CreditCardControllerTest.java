package br.com.fiap.card.credit.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.fiap.card.credit.dto.CreditCardDTO;
import br.com.fiap.card.credit.entity.CreditCard;
import br.com.fiap.card.credit.generic.AbstractTest;

public class CreditCardControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void returnAllStudents() throws Exception {
		String uri = "/students/";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		CreditCard[] arrCreditCard = super.mapFromJson(content, CreditCard[].class);
		assertTrue(arrCreditCard.length > 0);
	}

	@Test
	public void returnSpecificStudent() throws Exception {
		String uri = "/students/3095564 100-11";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();

		JSONObject obj = new JSONObject();

		obj.put("identification", "3095564 100-11");
		obj.put("studentName", "AARON FELIPE GRASSMANN");

		assertEquals(obj.toString(), content);
	}

	@Test
	public void createStudent() throws Exception {
		String uri = "/students";

		CreditCard creditCard = instantiateCreditCardDTO("1234567 890-89", "APOLLO CREED DAMASCENA");

		String inputJson = super.mapToJson(creditCard);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();

		JSONObject obj = new JSONObject();

		obj.put("identification", "1234567 890-89");
		obj.put("studentName", "APOLLO CREED DAMASCENA");

		assertEquals(obj.toString(), content);
	}

	@Test
	public void update() throws Exception {
		String uri = "/students/1616196 790-46";

		JSONObject objStudentSerialize = new JSONObject();
		objStudentSerialize.put("studentName", "ROMUALDO PEREIRA");

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.patch(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objStudentSerialize.toString())).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();

		JSONObject objStudentDeserialize = new JSONObject();

		objStudentDeserialize.put("identification", "1616196 790-46");
		objStudentDeserialize.put("studentName", "ROMUALDO PEREIRA");

		assertEquals(objStudentDeserialize.toString(), content);
	}

	@Test
	public void delete() throws Exception {
		String uri = "/students/3345594 710-13";

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	private CreditCard instantiateCreditCardDTO(String identification, String studentName) {
		CreditCardDTO createCreditCardDTO = new CreditCardDTO();
		createCreditCardDTO.setIdentification(identification);
		createCreditCardDTO.setStudentName(studentName);

		return new CreditCard(createCreditCardDTO);
	}
}