package com.marcosribeiro.dto;

import java.io.Serializable;
import java.util.Date;

import com.marcosribeiro.domain.Order;


public class OrderDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Date instant;
	
	public OrderDTO () {}
	
	public OrderDTO(Order order) {
		id = order.getId();
		instant = order.getInstant();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstant() {
		return instant;
	}

	public void setInstant(Date instant) {
		this.instant = instant;
	}
	
}
