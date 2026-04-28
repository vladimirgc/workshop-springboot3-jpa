package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.educandoweb.course.entities.Shipment;


public interface ShipmentRepository extends JpaRepository<Shipment, Long>{

}
