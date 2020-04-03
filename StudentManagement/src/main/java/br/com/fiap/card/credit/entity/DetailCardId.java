package br.com.fiap.card.credit.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DetailCardId implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="operation_id", nullable = false)
	private int operationId;
	
	@Column(name="student_id", nullable = false)
	private String studentId;
	
	public DetailCardId() {
		
	}

	public DetailCardId(int operationId, String studentId) {
		this.operationId = operationId;
		this.studentId = studentId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + operationId;
		result = prime * result + ((studentId == null) ? 0 : studentId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetailCardId other = (DetailCardId) obj;
		if (operationId != other.operationId)
			return false;
		if (studentId == null) {
			if (other.studentId != null)
				return false;
		} else if (!studentId.equals(other.studentId))
			return false;
		return true;
	}
	
	
}
