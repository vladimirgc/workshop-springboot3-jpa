package com.educandoweb.course.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.IndividualClient;
import com.educandoweb.course.entities.Segment;
import com.educandoweb.course.repositories.IndividualClientRepository;
import com.educandoweb.course.repositories.SegmentRepository;

@Configuration
@Profile({"dev", "prd"})
public class InitialDataConfig implements CommandLineRunner{
	
	@Autowired
	private IndividualClientRepository individualClientRepository;
	@Autowired
	private SegmentRepository segmentRepository;

	@Override
	public void run(String... args) throws Exception {
		if (individualClientRepository.findByName("USUARIO_FINAL").isEmpty()) {
			individualClientRepository.save(new IndividualClient(null, "", "", "", "", "", "", "", "", "00000000000", "USUARIO_FINAL"));
        }
		
		if (segmentRepository.findByName("Atelier").isEmpty()) {
			segmentRepository.save(new Segment(null, "Atelier", "#780481", true));
        }
		if (segmentRepository.findByName("Higiene").isEmpty()) {
			segmentRepository.save(new Segment(null, "Higiene", "#0505bd", true));
        }
	}

}
