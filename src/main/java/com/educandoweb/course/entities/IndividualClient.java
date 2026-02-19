package com.educandoweb.course.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_individual_client")
public class IndividualClient extends Client {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(nullable = false, unique = true)
	private String cpf;
	private String name;
	
	public IndividualClient(String cpf, String name) {
		super();
		this.cpf = cpf;
		this.name = name;
	}

	public IndividualClient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IndividualClient(Long id, String street, String number,
            String addressLine2, String city, String state,
            String postalCode, String email, String phone,
            String cpf, String name) {

			super(id, street, number, addressLine2, city, state,
			postalCode, email, phone);
			
			this.cpf = cpf;
			this.name = name;
	}


	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
