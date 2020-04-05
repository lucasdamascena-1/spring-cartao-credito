package br.com.fiap.card.credit.dto;

import br.com.fiap.card.credit.entity.CreditCard;

public class CreditCardDTO {

	private String identification;
	private String studentName;

	/* Default */
	public CreditCardDTO() {
	}

	/* Create */
	public CreditCardDTO(String identification, String studentName) {
		this.identification = identification;
		this.studentName = studentName;
	}

	/* Methods - Save / Update */
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