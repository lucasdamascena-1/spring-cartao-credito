package br.com.fiap.card.credit.dto;

public class DeleteCreditCardDetailsDTO {

	private int operationId;
	private String studentId;

	public int getOperationId() {
		return operationId;
	}
	public void setOperationId(int operationId) {
		this.operationId = operationId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}	

}
