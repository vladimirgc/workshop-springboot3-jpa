package com.educandoweb.course.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.educandoweb.course.entities.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("SELECT DISTINCT p FROM Product p JOIN p.categories c WHERE c.id = :categoryId")
	List<Product> findByCategoryId(@Param("categoryId") Long categoryId);

	Optional<Product> findByBarCode(String barCode);

    boolean existsByBarCode(String barCode);
    

	
}
