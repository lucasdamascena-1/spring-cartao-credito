package br.com.fiap.card.credit.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.fiap.card.credit.dto.CreateDetailCardDTO;

@Entity
@Table(name="tb_detail_card")
public class DetailCard {
	
	@EmbeddedId
	private DetailCardId id;
	
	@Column(name="description_operation")
	private String descriptionOperation;
	
	@Column(name="date_operation")
	private LocalDate dateOperation;
	
	@Column(name="type_operation")
	private char typeOperation;
	
	@Column(name="value_operation")
	private BigDecimal valueOperation;

	public DetailCard(DetailCardId id, String descriptionOperation, LocalDate dateOperation,
			char typeOperation, BigDecimal valueOperation) {
		this.id = id;
		this.descriptionOperation = descriptionOperation;
		this.dateOperation = dateOperation;
		this.typeOperation = typeOperation;
		this.valueOperation = valueOperation;
	}
	
	public DetailCard(CreateDetailCardDTO createDetailCardDTO) {
		DetailCardId idAux = new DetailCardId();
		idAux.setOperationId(createDetailCardDTO.getOperationId());
		idAux.setStudentId(createDetailCardDTO.getStudentId());
		this.id = idAux;
		this.descriptionOperation = createDetailCardDTO.getDescriptionOperation();
		this.dateOperation = createDetailCardDTO.getDateOperation();
		this.typeOperation = createDetailCardDTO.getTypeOperation();
		this.valueOperation = createDetailCardDTO.getValueOperation();
	}
	
    public DetailCard() {
    	
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

	public BigDecimal getValueOperation() {
		return valueOperation;
	}

	public void setValueOperation(BigDecimal valueOperation) {
		this.valueOperation = valueOperation;
	}

	public DetailCardId getId() {
		return id;
	}

	public void setId(DetailCardId id) {
		this.id = id;
	}

}
