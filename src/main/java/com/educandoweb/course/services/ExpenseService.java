package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Expense;
import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.entities.Shipment;
import com.educandoweb.course.repositories.ExpenseRepository;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.ShipmentRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ExpenseService {
	
	@Autowired
	private ExpenseRepository repository;
	
	public List<Expense> findAll(){
		
		return repository.findAll();
		
	}
	
	public Expense findById(Long id) {
		Optional<Expense> obj = repository.findById(id);
		return obj.get();
	}
	
	
	public Expense insert(Expense obj) {
		return repository.save(obj);
	}
	
	public Expense update(Long id, Expense obj) {
		try {
			Expense entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Expense entity, Expense obj) {
		entity.setAmount(obj.getAmount());
		entity.setCategory(obj.getCategory());
		entity.setDescription(obj.getDescription());
		entity.setMoment(obj.getMoment());
		entity.setReceiptUrl(obj.getReceiptUrl());
	}
	
	public void delete(Long id) {
		Optional<Expense> obj = repository.findById(id);
		if (obj.isPresent()) {
			try {
				repository.deleteById(id);
			} catch (DataIntegrityViolationException e) {
				throw new DatabaseException(e.getMessage());
			}
		} else {
			throw new ResourceNotFoundException(id);
		}
	} 
}
