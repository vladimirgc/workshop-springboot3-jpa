package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.Shipment;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.ShipmentRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ShipmentService {
	
	@Autowired
	private ShipmentRepository repository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Shipment> findAll(){
		
		return repository.findAll();
		
	}
	
	public Shipment findById(Long id) {
		Optional<Shipment> obj = repository.findById(id);
		return obj.get();
	}
	
	public Shipment insert(Shipment obj) {
		if (obj.getOrder() != null && obj.getOrder().getId() != null) {
	        Order order = orderRepository.findById(obj.getOrder().getId())
	            .orElseThrow(() -> new RuntimeException("Order not found"));

	        obj.setOrder(order);
	    }
		return repository.save(obj);
	}
	
	public Shipment update(Long id, Shipment obj) {
		try {
			Shipment entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Shipment entity, Shipment obj) {
		entity.setCarrier(obj.getCarrier());	
		entity.setPostedAt(obj.getPostedAt());
		entity.setTrackingCode(obj.getTrackingCode());
		entity.setShipmentImageUrl(obj.getShipmentImageUrl());
		entity.setNotes(obj.getNotes());
	}
	
	public void delete(Long id) {
		Optional<Shipment> obj = repository.findById(id);
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
