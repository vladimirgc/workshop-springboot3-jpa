package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.educandoweb.course.entities.enums.ExpenseCategory;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_expense")
public class Expense implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Instant moment;
	private String description;
	private Double amount;
	
	@Enumerated(EnumType.STRING)
	private ExpenseCategory category;	    
	private String receiptUrl;
	
	
	public Expense(Long id, Instant moment, String description, Double amount, ExpenseCategory category,
			String receiptUrl) {
		super();
		this.id = id;
		this.moment = moment;
		this.description = description;
		this.amount = amount;
		this.category = category;
		this.receiptUrl = receiptUrl;
	}


	public Expense() {
		super();
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Instant getMoment() {
		return moment;
	}


	public void setMoment(Instant moment) {
		this.moment = moment;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public ExpenseCategory getCategory() {
		return category;
	}


	public void setCategory(ExpenseCategory category) {
		this.category = category;
	}


	public String getReceiptUrl() {
		return receiptUrl;
	}


	public void setReceiptUrl(String receiptUrl) {
		this.receiptUrl = receiptUrl;
	}


	@Override
	public int hashCode() {
		return Objects.hash(category, id, moment);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expense other = (Expense) obj;
		return category == other.category && Objects.equals(id, other.id) && Objects.equals(moment, other.moment);
	}
	
	
	

}
