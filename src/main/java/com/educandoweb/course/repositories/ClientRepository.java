package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
