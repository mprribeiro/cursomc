package com.marcosribeiro.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.marcosribeiro.domain.enums.Profile;
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
	
	@Autowired
	private BCryptPasswordEncoder pe;

	public void instantiateDatabase() throws ParseException {
		
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		Category cat3 = new Category(null, "Clothing, bed & bath");
		Category cat4 = new Category(null, "Eletronics");
		Category cat5 = new Category(null, "Gardening");
		Category cat6 = new Category(null, "Decoration");
		Category cat7 = new Category(null, "Perfumery");
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Office table", 300.00);
		Product p5 = new Product(null, "Towel", 50.00);
		Product p6 = new Product(null, "Quilt", 200.00);
		Product p7 = new Product(null, "True color TV", 1200.00);
		Product p8 = new Product(null, "Brush cutter", 800.00);
		Product p9 = new Product(null, "Beside lamp", 100.00);
		Product p10 = new Product(null, "Pendant", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9, p10));
		cat7.getProducts().addAll(Arrays.asList(p11));
		
		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		State est1 = new State(null, "Minas Gerais");
		State est2 = new State(null, "São Paulo");
		State est3 = new State(null, "Rio de Janeiro");
		
		City c1 = new City(null, "Uberlândia", est1);
		City c2 = new City(null, "São Paulo", est2);
		City c3 = new City(null, "Campinas", est2);
		City c4 = new City(null, "Rio de Janeiro", est3);
		
		est1.getCities().addAll(Arrays.asList(c1));
		est2.getCities().addAll(Arrays.asList(c2, c3));
		est1.getCities().addAll(Arrays.asList(c4));
		
		stateRepository.saveAll(Arrays.asList(est1, est2, est3));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3, c4));
		
		Client cli1 = new Client(null, "Marcos Paulo", "mprribeiro1902@gmail.com", "42158078609", ClientType.PERSON, pe.encode("123"));
		cli1.getPhones().addAll(Arrays.asList("12981162395", "1239668939"));
		
		Client cli2 = new Client(null, "Pâmela Rodrigues", "markos-santista@outlook.com", "12345678900", ClientType.PERSON, pe.encode("123"));
		cli2.getPhones().addAll(Arrays.asList("11981162300", "1139668900"));
		cli2.addProfile(Profile.ADMIN);
		
		Address ad1 = new Address(null, "Rua Flores", "300", "Apto 203", "Jardim Flórida", "12234567", cli1, c1);
		Address ad2 = new Address(null, "Rua Santo Expedito", "803", "", "Res. União", "12239021", cli1, c2);
		Address ad3 = new Address(null, "Rua Copacabana", "557", "Apto 101", "Leblon", "3456798", cli2, c4);
		
		cli1.getAddresses().addAll(Arrays.asList(ad1, ad2));
		cli2.getAddresses().addAll(Arrays.asList(ad3));
		
		clientRepository.saveAll(Arrays.asList(cli1, cli2));
		addressRepository.saveAll(Arrays.asList(ad1, ad2, ad3));
		
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
