package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Client;
import com.educandoweb.course.entities.CorporateClient;
import com.educandoweb.course.entities.IndividualClient;
import com.educandoweb.course.repositories.ClientRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	public List<Client> findAll(){
		
		return repository.findAll();
		
	}
	
	public Client findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Client insert(Client obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		Optional<Client> obj = repository.findById(id);
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
	
	
	public Client update(Long id, Client obj) {
		 try {
		        Client entity = repository.findById(id)
		                .orElseThrow(() -> new ResourceNotFoundException(id));

		        updateData(entity, obj);

		        return repository.save(entity);

		    } catch (EntityNotFoundException e) {
		        throw new ResourceNotFoundException(id);
		    }
		
		/*
		try {
		Client entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}*/
	}

	private void updateData(Client entity, Client obj) {
		// Campos comuns a todos os clientes
	    entity.setEmail(obj.getEmail());
	    entity.setPhone(obj.getPhone());
	    entity.setStreet(obj.getStreet());
	    entity.setNumber(obj.getNumber());
	    entity.setAddressLine2(obj.getAddressLine2());
	    entity.setCity(obj.getCity());
	    entity.setState(obj.getState());
	    entity.setPostalCode(obj.getPostalCode());

	    // Pessoa Física
	    if (entity instanceof IndividualClient
	            && obj instanceof IndividualClient) {

	        IndividualClient entityPF = (IndividualClient) entity;
	        IndividualClient objPF = (IndividualClient) obj;

	        entityPF.setName(objPF.getName());
	        entityPF.setCpf(objPF.getCpf());
	    }

	    // Pessoa Jurídica
	    if (entity instanceof CorporateClient
	            && obj instanceof CorporateClient) {

	        CorporateClient entityPJ = (CorporateClient) entity;
	        CorporateClient objPJ = (CorporateClient) obj;

	        entityPJ.setCorporateName(objPJ.getCorporateName());
	        entityPJ.setCnpj(objPJ.getCnpj());
	        entityPJ.setStateRegistration(objPJ.getStateRegistration());
	    }		
	}
	
}
