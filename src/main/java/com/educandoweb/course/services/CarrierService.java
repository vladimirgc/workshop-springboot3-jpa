package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import com.educandoweb.course.entities.Carrier;

import com.educandoweb.course.repositories.CarrierRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CarrierService {
	
	@Autowired
	private CarrierRepository repository;
	
	public List<Carrier> findAll(){
		
		return repository.findAll();
		
	}
	
	public Carrier findById(Long id) {
		Optional<Carrier> obj = repository.findById(id);
		return obj.get();
	}
	
	public Carrier insert(Carrier obj) {
		return repository.save(obj);
	}
	
	public Carrier update(Long id, Carrier obj) {
		try {
			Carrier entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Carrier entity, Carrier obj) {
		entity.setName(obj.getName());				
	}
	
	public void delete(Long id) {
		Optional<Carrier> obj = repository.findById(id);
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
