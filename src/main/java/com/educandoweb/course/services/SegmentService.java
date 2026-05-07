package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import com.educandoweb.course.entities.Segment;
import com.educandoweb.course.repositories.SegmentRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SegmentService {
	
	@Autowired
	private SegmentRepository repository;
	
	public List<Segment> findAll(){
		
		return repository.findAll();
		
	}
	
	public Segment findById(Long id) {
		Optional<Segment> obj = repository.findById(id);
		return obj.get();
	}
	
	public Segment insert(Segment obj) {
		return repository.save(obj);
	}
	
	public Segment update(Long id, Segment obj) {
		try {
			Segment entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Segment entity, Segment obj) {
		entity.setName(obj.getName());
		entity.setColor(obj.getColor());
		entity.setActive(obj.getActive());
	}
	
	public void delete(Long id) {
		Optional<Segment> obj = repository.findById(id);
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
