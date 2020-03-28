package br.com.fiap.card.credit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.fiap.card.credit.dto.CreateCreditCardDTO;

@Entity
@Table(name = "Student")
public class CreditCard {

	@Id
	private String identification;

	@Column
	private String studentName;

	public CreditCard() {
	}

	public CreditCard(CreateCreditCardDTO creditCardDTO) {
		this.identification = creditCardDTO.getIdentification();
		this.studentName = creditCardDTO.getStudentName();
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
