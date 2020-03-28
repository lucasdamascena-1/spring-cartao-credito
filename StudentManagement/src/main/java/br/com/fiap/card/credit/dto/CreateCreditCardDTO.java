package br.com.fiap.card.credit.dto;

public class CreateCreditCardDTO {

	private String identification;
	private String studentName;

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