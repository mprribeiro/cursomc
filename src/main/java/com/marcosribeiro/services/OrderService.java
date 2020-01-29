package com.marcosribeiro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcosribeiro.domain.Order;
import com.marcosribeiro.repository.OrderRepository;
import com.marcosribeiro.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public Order find(Integer id) {
		Optional<Order> obj = orderRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Order.class.getName()));
		}
	
	public List<Order> findAll() {
		List<Order> obj = orderRepository.findAll();
		return obj;
	}
}
