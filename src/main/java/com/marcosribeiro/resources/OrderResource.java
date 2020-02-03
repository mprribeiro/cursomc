package com.marcosribeiro.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marcosribeiro.domain.Order;
import com.marcosribeiro.dto.OrderDTO;
import com.marcosribeiro.services.OrderService;

@RestController
@RequestMapping(value="/orders")
public class OrderResource {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<OrderDTO>> findAll() {
		List<Order> list = orderService.findAll();
		List<OrderDTO> listDTO= list.stream().map(obj -> new OrderDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Order> find(@PathVariable Integer id) {
		Order obj = orderService.find(id);
		return ResponseEntity.ok(obj);
	}
			
}
