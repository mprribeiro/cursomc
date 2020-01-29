package com.marcosribeiro.resources;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marcosribeiro.domain.Order;
import com.marcosribeiro.services.OrderService;

import java.util.List;

@RestController
@RequestMapping(value="/orders")
public class OrderResource {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Order> retrieveAll() {
		return orderService.findAll();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> retrieve(@PathVariable Integer id) {
		Order obj = orderService.find(id);
		return ResponseEntity.ok(obj);
	}
			
}