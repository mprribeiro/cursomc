package com.marcosribeiro.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcosribeiro.domain.Address;
import com.marcosribeiro.domain.Category;
import com.marcosribeiro.domain.City;
import com.marcosribeiro.domain.Client;
import com.marcosribeiro.domain.Order;
import com.marcosribeiro.domain.OrderedItem;
import com.marcosribeiro.domain.Payment;
import com.marcosribeiro.domain.PaymentWithCard;
import com.marcosribeiro.domain.PaymentWithSlip;
import com.marcosribeiro.domain.Product;
import com.marcosribeiro.domain.State;
import com.marcosribeiro.domain.enums.ClientType;
import com.marcosribeiro.domain.enums.PaymentStatus;
import com.marcosribeiro.repository.AddressRepository;
import com.marcosribeiro.repository.CategoryRepository;
import com.marcosribeiro.repository.CityRepository;
import com.marcosribeiro.repository.ClientRepository;
import com.marcosribeiro.repository.OrderRepository;
import com.marcosribeiro.repository.OrderedItemRepository;
import com.marcosribeiro.repository.PaymentRepository;
import com.marcosribeiro.repository.ProductRepository;
import com.marcosribeiro.repository.StateRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderedItemRepository orderedItemRepository;

	public void instantiateDatabase() throws ParseException {
		
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		State est1 = new State(null, "Minas Gerais");
		State est2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Uberlândia", est1);
		City c2 = new City(null, "São Paulo", est2);
		City c3 = new City(null, "Campinas", est2);
		
		est1.getCities().addAll(Arrays.asList(c1));
		est2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateRepository.saveAll(Arrays.asList(est1, est2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "42158078609", ClientType.PERSON);
		cli1.getPhones().addAll(Arrays.asList("12981162395", "1239668939"));
		
		Address ad1 = new Address(null, "Rua Flores", "300", "Apto 203", "Jardim Flórida", "12234567", cli1, c1);
		Address ad2 = new Address(null, "Rua Santo Expedito", "803", "", "Res. União", "12239021", cli1, c2);
		
		cli1.getAddresses().addAll(Arrays.asList(ad1, ad2));
		
		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(ad1, ad2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Order ord1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1, ad1);
		Order ord2 = new Order(null, sdf.parse("10/10/2017 19:35"), cli1, ad2);
		
		Payment pay1 = new PaymentWithCard(null, PaymentStatus.APPROVED, ord1, 6);
		ord1.setPayment(pay1);
		
		Payment pay2 = new PaymentWithSlip(null, PaymentStatus.PENDING, ord2, sdf.parse("20/10/2017 00:00"), null);
		ord2.setPayment(pay2);
		
		cli1.getOrders().addAll(Arrays.asList(ord1, ord2));
		
		orderRepository.saveAll(Arrays.asList(ord1, ord2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));
		
		OrderedItem oi1 = new OrderedItem(ord1, p1, 0.00, 1, 2000.00);
		OrderedItem oi2 = new OrderedItem(ord1, p3, 0.00, 2, 80.00);
		OrderedItem oi3 = new OrderedItem(ord2, p2, 100.00, 1, 800.00);
		
		ord1.getItems().addAll(Arrays.asList(oi1, oi2));
		ord2.getItems().addAll(Arrays.asList(oi3));
		
		p1.getItems().addAll(Arrays.asList(oi1));
		p2.getItems().addAll(Arrays.asList(oi3));
		p3.getItems().addAll(Arrays.asList(oi2));
		
		orderedItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));
	}
}
