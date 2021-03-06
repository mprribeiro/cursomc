package com.marcosribeiro.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.marcosribeiro.services.validation.ClientInsert;

@ClientInsert
public class ClientNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5,max=120, message="Deve conter entre 5 e 80 caracteres")
	private String name;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	private String register;
	private Integer type;
	
	@NotEmpty
	private String password;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String street;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String number;
	private String complement;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String neighborhood;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String cep;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String phone1;
	private String phone2;
	private String phone3;
	
	private Integer cityID;
	
	private Integer stateID;
	
	private byte[] clientImg;
	
	public ClientNewDTO( ) {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Integer getCityID() {
		return cityID;
	}

	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStateID() {
		return stateID;
	}

	public void setStateID(Integer stateID) {
		this.stateID = stateID;
	}
	
	public byte[] getClientImg() {
		return clientImg;
	}

	public void setClientImg(byte[] clientImg) {
		this.clientImg = clientImg;
	}
}
