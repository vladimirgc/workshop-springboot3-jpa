package com.educandoweb.course.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.Category;



public interface CategoryRepository extends JpaRepository<Category, Long>{
	Optional<Category> findByName(String name);
}
