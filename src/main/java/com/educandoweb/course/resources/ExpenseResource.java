package com.educandoweb.course.resources;

import java.net.URI;
import java.util.Arrays;
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

import com.educandoweb.course.entities.Expense;
import com.educandoweb.course.entities.enums.ExpenseCategory;
import com.educandoweb.course.services.ExpenseService;
import com.educandoweb.course.services.UploadService;



@RestController
@RequestMapping(value = "/expenses")
@CrossOrigin(origins = "http://localhost:5173") // Vite
public class ExpenseResource {

	@Autowired
	private ExpenseService service;
	
	@Autowired
	private UploadService uploadService;
	
	@GetMapping
	public ResponseEntity<List<Expense>> findAll(){
		List<Expense> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
		
	@GetMapping("/categories")
	public List<String> getCategories() {
	    return Arrays.stream(ExpenseCategory.values())
	              .map(Enum::name)
	              .toList();
	}	
	 
	@PostMapping
	public ResponseEntity<Expense> insert(@RequestBody Expense obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Expense> update(@PathVariable Long id, @RequestBody Expense obj){
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
	    String url = uploadService.save(file, "expenses");
	    return ResponseEntity.ok(Map.of("url", url));
	 }
	
}
