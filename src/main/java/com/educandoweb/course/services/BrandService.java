package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Brand;
import com.educandoweb.course.repositories.BrandRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BrandService {
	
	@Autowired
	private BrandRepository repository;
	
	public List<Brand> findAll(){
		
		return repository.findAll();
		
	}
	
	public Brand findById(Long id) {
		Optional<Brand> obj = repository.findById(id);
		return obj.get();
	}
	
	public Brand insert(Brand obj) {
		return repository.save(obj);
	}
	
	public Brand update(Long id, Brand obj) {
		try {
		Brand entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Brand entity, Brand obj) {
		entity.setName(obj.getName());				
	}
	
	public void delete(Long id) {
		Optional<Brand> obj = repository.findById(id);
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
