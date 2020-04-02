package br.com.fiap.card.credit.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.fiap.card.credit.dto.TransactionCardDTO;

@Entity
@Table(name="tb_detail_card")
public class TransactionCard {
	
	@Id
	@Column(name="idOperacao")
	private int operationId;
	
	@Column(name="idEstudante")
	private String studentId;
	
	@Column(name="descricaoOperacao")
	private String descriptionOperation;
	
	@Column(name="dataOperacao")
	private LocalDate dateOperation;
	
	@Column(name="tipoOperacao")
	private char typeOperation;
	
	@Column(name="valorOperacao")
	private float valueOperation;

	public TransactionCard(int operationId, String studentId, String descriptionOperation, LocalDate dateOperation,
			char typeOperation, float valueOperation) {
		super();
		this.operationId = operationId;
		this.studentId = studentId;
		this.descriptionOperation = descriptionOperation;
		this.dateOperation = dateOperation;
		this.typeOperation = typeOperation;
		this.valueOperation = valueOperation;
	}
	
	public TransactionCard(TransactionCardDTO transactionCardDTO) {
		this.operationId = transactionCardDTO.getOperationId();
		this.studentId = transactionCardDTO.getStudentId();
		this.descriptionOperation = transactionCardDTO.getDescriptionOperation();
		this.dateOperation = transactionCardDTO.getDateOperation();
		this.typeOperation = transactionCardDTO.getTypeOperation();
		this.valueOperation = transactionCardDTO.getValueOperation();
	}
	
    public TransactionCard() {
    	
    }

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

	public String getDescriptionOperation() {
		return descriptionOperation;
	}

	public void setDescriptionOperation(String descriptionOperation) {
		this.descriptionOperation = descriptionOperation;
	}

	public LocalDate getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(LocalDate dateOperation) {
		this.dateOperation = dateOperation;
	}

	public char getTypeOperation() {
		return typeOperation;
	}

	public void setTypeOperation(char typeOperation) {
		this.typeOperation = typeOperation;
	}

	public float getValueOperation() {
		return valueOperation;
	}

	public void setValueOperation(float valueOperation) {
		this.valueOperation = valueOperation;
	}
    
    
	

}
