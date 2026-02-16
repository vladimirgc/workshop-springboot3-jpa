package com.educandoweb.course.services;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.OrderSequence;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.OrderSequenceRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private OrderSequenceRepository orderSequenceRepository;
	
	
	@Transactional
	public Long gerarNumeroPedido() {
	    int anoAtual = Year.now().getValue();

	    OrderSequence seq = orderSequenceRepository.findById(anoAtual)
	            .orElseGet(() -> {
	                OrderSequence newSeq = new OrderSequence();
	                newSeq.setAno(anoAtual);
	                newSeq.setUltimaSequencia(0);
	                // Salva imediatamente no banco para garantir inserção
	                return orderSequenceRepository.save(newSeq);
	            });

	    int novaSequencia = seq.getUltimaSequencia() + 1;
	    seq.setUltimaSequencia(novaSequencia);
	    orderSequenceRepository.save(seq);

	    // Converte ano + sequência em Long
	    String numeroStr = anoAtual + String.format("%04d", novaSequencia);
	    return Long.parseLong(numeroStr);
	}
	
	public List<Order> findAll(){
		
		return repository.findAll();
		
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}
	
	public Order insert(Order order) {
		order.setNumero(gerarNumeroPedido());
        return repository.save(order);
    }
	
	public Order update(Long id, Order obj) {
        try {
            Order entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Pedido não encontrado. ID: " + id);
        }
    }
    
    private void updateData(Order entity, Order obj) {
        entity.setMoment(obj.getMoment());
        entity.setOrderStatus(obj.getOrderStatus());
        entity.setClient(obj.getClient());
    }

    public void delete(Long id) {
        Optional<Order> obj = repository.findById(id);
        if (obj.isPresent()) {
            try {
                repository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new DatabaseException("Erro de integridade: " + e.getMessage());
            }
        } else {
            throw new ResourceNotFoundException("Pedido não encontrado. ID: " + id);
        }
    }


}
