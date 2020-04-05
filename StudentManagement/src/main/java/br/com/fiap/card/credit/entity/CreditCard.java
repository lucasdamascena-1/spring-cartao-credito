package br.com.fiap.card.credit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.fiap.card.credit.dto.CreditCardDTO;

@Entity
@Table(name = "tb_student")
public class CreditCard {

	@Id
	private String identification;

	@Column(name = "student")
	private String studentName;

	public CreditCard() {
	}

	public CreditCard(CreditCardDTO createCreditCardDTO) {
		this.identification = createCreditCardDTO.getIdentification();
		this.studentName = createCreditCardDTO.getStudentName();
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
