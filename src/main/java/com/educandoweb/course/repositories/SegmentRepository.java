package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.educandoweb.course.entities.Segment;


public interface SegmentRepository extends JpaRepository<Segment, Long>{

}
