package com.marcosribeiro.services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcosribeiro.domain.Client;
import com.marcosribeiro.domain.Order;
import com.marcosribeiro.domain.OrderedItem;
import com.marcosribeiro.domain.PaymentWithSlip;
import com.marcosribeiro.domain.enums.PaymentStatus;
import com.marcosribeiro.repository.OrderRepository;
import com.marcosribeiro.repository.OrderedItemRepository;
import com.marcosribeiro.repository.PaymentRepository;
import com.marcosribeiro.security.UserSS;
import com.marcosribeiro.services.exceptions.AuthorizationException;
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
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmailService emailService;

	
	public Order find(Integer id) {
		Optional<Order> obj = orderRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Type: " + Order.class.getName()));
	}
	
	public List<Order> findAll() {
		List<Order> obj = orderRepository.findAll();
		return obj;
	}
	
	@Transactional
	public Order insert (Order order) {
		order.setId(null);
		order.setInstant(new Date());
		order.setClient(clientService.find(order.getClient().getId()));
		order.getPayment().setStatus(PaymentStatus.PENDING);
		order.getPayment().setOrder(order);
		if (order.getPayment() instanceof PaymentWithSlip) {
			PaymentWithSlip pgto = (PaymentWithSlip) order.getPayment();
			slipService.fillPaymentWithSlip(pgto, order.getInstant());
		}
		orderRepository.saveAll(Arrays.asList(order));
		paymentRepository.saveAll(Arrays.asList(order.getPayment()));
		for (OrderedItem oi : order.getItems()) {
			oi.setDiscount(0.0);
			oi.setProduct(productService.find(oi.getProduct().getId()));
			oi.setPrice(oi.getProduct().getPrice());
			oi.setOrder(order);
		}
		orderedItemRepository.saveAll(order.getItems());
		emailService.sendOrderConfirmationHtmlEmail(order);
		return order;	
	}
	
	public Page<Order> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Access denied!");
		}
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Client client = clientService.find(user.getId());
		
		return orderRepository.findByClient(client, pageRequest);
		
	}
}
