package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.educandoweb.course.entities.Product;
import com.educandoweb.course.services.ProductService;
import com.educandoweb.course.services.UploadService;



@RestController
@RequestMapping(value = "/products")
@CrossOrigin(origins = "http://localhost:5173") // Vite
public class ProductResource {

	@Autowired
	private ProductService service;
	
	@Autowired
	private UploadService uploadService;
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id){
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/barcode/{barCode}")
	public ResponseEntity<Product> findByBarcode(@PathVariable String barCode) {
	    Product product = service.findByBarcode(barCode);
	    if (product == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok(product);
	}

	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<Product>> findByCategoryId(@PathVariable Long categoryId) {
	    List<Product> list = service.findByCategoryId(categoryId);
	    return ResponseEntity.ok().body(list);
	}

	
	@PostMapping
	public ResponseEntity<Product> insert(@RequestBody Product obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	 @PostMapping("/upload")
	 public ResponseEntity<Map<String, String>> uploadImage(
	     @RequestParam("file") MultipartFile file) {
	     String url = uploadService.save(file);
	     return ResponseEntity.ok(Map.of("url", url));
	 }
	
}
