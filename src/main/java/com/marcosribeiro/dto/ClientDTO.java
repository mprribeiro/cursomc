package com.marcosribeiro.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.marcosribeiro.domain.Client;
import com.marcosribeiro.services.validation.ClientUpdate;

@ClientUpdate
public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5,max=120, message="Deve conter entre 5 e 80 caracteres")
	private String name;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	private String clientImg;

	public ClientDTO () {}
	
	public ClientDTO(Client client) {
		id = client.getId();
		name = client.getName();
		email = client.getEmail();
		clientImg = client.getClientImg();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getClientImg() {
		return clientImg;
	}

	public void setClientImg(String clientImg) {
		this.clientImg = clientImg;
	}
	
}
