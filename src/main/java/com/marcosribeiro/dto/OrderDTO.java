package com.marcosribeiro.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marcosribeiro.domain.Address;
import com.marcosribeiro.domain.Order;
import com.marcosribeiro.domain.OrderedItem;
import com.marcosribeiro.domain.Payment;

public class OrderDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String clientName;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date instant;
	
	private Payment payment;
	
	private Address deliveryAddress;
	
	private Set<OrderedItem> items = new HashSet<>();
	
	public OrderDTO() {}

	public OrderDTO(Order order) {
		this.clientName = order.getClient().getName();
		this.instant = order.getInstant();
		this.payment = order.getPayment();
		this.deliveryAddress = order.getDeliveryAddress();
	}
	
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Date getInstant() {
		return instant;
	}

	public void setInstant(Date instant) {
		this.instant = instant;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	
	public Set<OrderedItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderedItem> items) {
		this.items = items;
	}
	
}
