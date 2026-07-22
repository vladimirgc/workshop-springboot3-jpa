package com.educandoweb.course.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.IndividualClient;

public interface IndividualClientRepository extends JpaRepository<IndividualClient, Long>{
	Optional<IndividualClient> findByName(String name);

}
