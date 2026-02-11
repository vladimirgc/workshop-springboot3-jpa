package com.educandoweb.course.services;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;



import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll(){
		
		return repository.findAll();
		
	}
	
	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}
	
	public Product insert(Product obj) {
		return repository.save(obj);
	}
	
	public Product update(Long id, Product obj) {
		try {
		Product entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Product entity, Product obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setPrice(obj.getPrice());
		entity.setImgUrl(obj.getImgUrl());
	}
	
	public void delete(Long id) {
		Optional<Product> obj = repository.findById(id);
		if (obj.isPresent()) {
			Product product = obj.get();
			if (product.getImgUrl() != null && !product.getImgUrl().isEmpty()) {
	            String uploadDir = "uploads/products/";
	            String imageUrl = product.getImgUrl();
	            String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
	            File file = new File(uploadDir + fileName);

	            if (file.exists()) {
	                file.delete();
	            }
	        }
			try {
				repository.deleteById(id);
			} catch (DataIntegrityViolationException e) {
				throw new DatabaseException(e.getMessage());
			}
		} else {
			throw new ResourceNotFoundException(id);
		}
	} 

	public List<Product> findByCategoryId(Long categoryId) {		
		
	    if (categoryId != null) {
	        return repository.findByCategoryId(categoryId);
	    }

	    return repository.findAll();
	}

	
}
