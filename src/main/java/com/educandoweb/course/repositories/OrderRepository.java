package com.educandoweb.course.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.educandoweb.course.entities.Order;


public interface OrderRepository extends JpaRepository<Order, Long>{
	Optional<Order> findByNumero(Long numero);

}
