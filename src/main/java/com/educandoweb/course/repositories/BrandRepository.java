package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.educandoweb.course.entities.Brand;


public interface BrandRepository extends JpaRepository<Brand, Long>{

}
