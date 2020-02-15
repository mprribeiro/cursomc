package com.marcosribeiro.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.marcosribeiro.domain.City;

public class CityDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=1,max=120, message="Deve conter entre 1 e 80 caracteres")
	private String name;

	public CityDTO () {}
	
	public CityDTO(City city) {
		name = city.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
