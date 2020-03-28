package br.com.fiap.card.credit.dto;

import br.com.fiap.card.credit.entity.CreditCard;

public class CreditCardDTO {

	private String identification;
	private String studentName;

	public CreditCardDTO(String identification, String studentName) {
		super();
		this.identification = identification;
		this.studentName = studentName;
	}

	public CreditCardDTO(CreateCreditCardDTO createCreditCardDTO) {
		super();
		this.identification = createCreditCardDTO.getIdentification();
		this.studentName = createCreditCardDTO.getStudentName();
	}

	public CreditCardDTO(CreditCard creditCard) {
		this.identification = creditCard.getIdentification();
		this.studentName = creditCard.getStudentName();
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
}