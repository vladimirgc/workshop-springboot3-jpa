package com.educandoweb.course.resources;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.services.OrderService;


@RestController
@RequestMapping(value = "/orders")
@CrossOrigin(origins = "http://localhost:5173") // Vite
public class OrderResource {

	@Autowired
	private OrderService service;
	
	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id){
		Order obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/status")
	public ResponseEntity<List<Map<String, Object>>> getOrderStatus() {
	    List<Map<String, Object>> statusList = Arrays.stream(OrderStatus.values())
	        .map(s -> {
	            Map<String, Object> m = new HashMap<>();
	            m.put("code", s.getCode());
	            m.put("name", s.name());
	            return m;
	        })
	        .collect(Collectors.toList());
	    return ResponseEntity.ok(statusList);
	}

	@PostMapping
	public ResponseEntity<Order> insert(@RequestBody Order obj){
	    obj = service.insert(obj); 	   
	    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	    return ResponseEntity.created(uri).body(obj);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order obj) {
	    obj = service.update(id, obj);
	    return ResponseEntity.ok(obj);
	}

	    
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
	    service.delete(id);
	    return ResponseEntity.noContent().build();
	    }
	
}
