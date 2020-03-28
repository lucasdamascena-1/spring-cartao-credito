package br.com.fiap.card.credit.dto;

public class StudentDTO {

	private String identity;
	private String name;

	public StudentDTO(String identity, String name) {
		super();
		this.identity = identity;
		this.name = name;
	}

	public StudentDTO(CreateStudentDTO createStudentDTO) {
		super();
		this.identity = createStudentDTO.getIdentity();
		this.name = createStudentDTO.getName();
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}