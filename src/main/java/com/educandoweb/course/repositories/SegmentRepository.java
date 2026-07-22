package com.educandoweb.course.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.educandoweb.course.entities.Segment;


public interface SegmentRepository extends JpaRepository<Segment, Long>{
	Optional<Segment> findByName(String name);

}
