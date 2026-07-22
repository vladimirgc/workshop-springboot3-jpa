package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import com.educandoweb.course.entities.Revenue;
import com.educandoweb.course.repositories.RevenueRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RevenueService {
	
	@Autowired
	private RevenueRepository repository;
	
	public List<Revenue> findAll(){
		
		return repository.findAll();
		
	}
	
	public Revenue findById(Long id) {
		Optional<Revenue> obj = repository.findById(id);
		return obj.get();
	}
	
	
	public Revenue insert(Revenue obj) {
		return repository.save(obj);
	}
	
	public Revenue update(Long id, Revenue obj) {
		try {
			Revenue entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Revenue entity, Revenue obj) {
		entity.setAmount(obj.getAmount());
		entity.setDescription(obj.getDescription());
		entity.setMoment(obj.getMoment());
	}
	
	public void delete(Long id) {
		Optional<Revenue> obj = repository.findById(id);
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
