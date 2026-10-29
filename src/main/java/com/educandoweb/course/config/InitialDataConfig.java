package com.educandoweb.course.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.Brand;
import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.IndividualClient;
import com.educandoweb.course.entities.Segment;
import com.educandoweb.course.repositories.BrandRepository;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.IndividualClientRepository;
import com.educandoweb.course.repositories.SegmentRepository;

@Configuration
@Profile({"dev", "prd"})
public class InitialDataConfig implements CommandLineRunner{
	
	@Autowired
	private IndividualClientRepository individualClientRepository;
	@Autowired
	private SegmentRepository segmentRepository;
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private CategoryRepository categoryRepository;

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
		
		if (brandRepository.findByName("Kambadiami").isEmpty()) {
			brandRepository.save(new Brand(null, "Kambadiami"));
        }
		if (brandRepository.findByName("Cordex").isEmpty()) {
			brandRepository.save(new Brand(null, "Cordex"));
        }
		
		if (categoryRepository.findByName("Limpeza").isEmpty()) {
			categoryRepository.save(new Category(null, "Limpeza"));
        }
		if (categoryRepository.findByName("Descartáveis").isEmpty()) {
			categoryRepository.save(new Category(null, "Descartáveis"));
        }
		if (categoryRepository.findByName("Papéis").isEmpty()) {
			categoryRepository.save(new Category(null, "Papéis"));
        }
		if (categoryRepository.findByName("Bolsa").isEmpty()) {
			categoryRepository.save(new Category(null, "Bolsa"));
        }
		if (categoryRepository.findByName("Mochila").isEmpty()) {
			categoryRepository.save(new Category(null, "Mochila"));
        }
		if (categoryRepository.findByName("Acessórios").isEmpty()) {
			categoryRepository.save(new Category(null, "Acessórios"));
        }
		if (categoryRepository.findByName("Necessaire").isEmpty()) {
			categoryRepository.save(new Category(null, "Necessaire"));
        }
	}

}
