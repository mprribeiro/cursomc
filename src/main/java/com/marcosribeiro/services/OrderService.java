package com.marcosribeiro.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcosribeiro.domain.Order;
import com.marcosribeiro.domain.OrderedItem;
import com.marcosribeiro.domain.PaymentWithSlip;
import com.marcosribeiro.domain.enums.PaymentStatus;
import com.marcosribeiro.repository.OrderRepository;
import com.marcosribeiro.repository.OrderedItemRepository;
import com.marcosribeiro.repository.PaymentRepository;
import com.marcosribeiro.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private SlipService slipService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderedItemRepository orderedItemRepository;
	
	@Autowired
	private ProductService productService;
	
	public Order find(Integer id) {
		Optional<Order> obj = orderRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}
	
	public List<Order> findAll() {
		List<Order> obj = orderRepository.findAll();
		return obj;
	}
	
	@Transactional
	public Order insert (Order order) {
		order.setId(null);
		order.setInstant(new Date());
		order.getPayment().setStatus(PaymentStatus.PENDING);
		order.getPayment().setOrder(order);
		if (order.getPayment() instanceof PaymentWithSlip) {
			PaymentWithSlip pgto = (PaymentWithSlip) order.getPayment();
			slipService.fillPaymentWithSlipe(pgto, order.getInstant());
		}
		order = orderRepository.save(order);
		paymentRepository.save(order.getPayment());
		for (OrderedItem oi : order.getItems()) {
			oi.setDiscount(0.0);
			oi.setPrice(productService.find(oi.getProduct().getId()).getPrice());
			oi.setOrder(order);
		}
		orderedItemRepository.saveAll(order.getItems());
		return order;
	}
}
