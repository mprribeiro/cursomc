package com.marcosribeiro.dto;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.marcosribeiro.domain.Order;


public class OrderDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String instant;
	
	public OrderDTO () {}
	
	public OrderDTO(Order order) {
		id = order.getId();
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		instant = sdf.format(order.getInstant());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInstant() {
		return instant;
	}

	public void setInstant(String instant) {
		this.instant = instant;
	}
	
}
