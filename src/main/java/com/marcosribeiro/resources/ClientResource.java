package com.marcosribeiro.resources;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marcosribeiro.domain.Client;
import com.marcosribeiro.services.ClientService;

import java.util.List;

@RestController
@RequestMapping(value="/clients")
public class ClientResource {

	@Autowired
	private ClientService clientService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Client> retrieveAll() {
		return clientService.findAll();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> retrieve(@PathVariable Integer id) {
		Client obj = clientService.find(id);
		return ResponseEntity.ok(obj);
	}
			
}
