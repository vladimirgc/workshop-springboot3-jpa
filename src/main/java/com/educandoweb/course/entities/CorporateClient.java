package com.educandoweb.course.entities;

import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_corporate_client")
public class CorporateClient extends Client {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(nullable = false, unique = true)
	private String cnpj;
	private String corporateName;
	private String stateRegistration;
	
	public CorporateClient(String cnpj, String corporateName, String stateRegistration) {
		super();
		this.cnpj = cnpj;
		this.corporateName = corporateName;
		this.stateRegistration = stateRegistration;
	}

	public CorporateClient() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CorporateClient(Long id, String street, String number,
            String addressLine2, String city, String state,
            String postalCode, String email, String phone,
            String cnpj, String corporateName,
            String stateRegistration) {

			super(id, street, number, addressLine2, city, state,
				postalCode, email, phone);

			this.cnpj = cnpj;
			this.corporateName = corporateName;
			this.stateRegistration = stateRegistration;
}


	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	public String getStateRegistration() {
		return stateRegistration;
	}

	public void setStateRegistration(String stateRegistration) {
		this.stateRegistration = stateRegistration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cnpj, corporateName);
		return result;
	}

	
}
