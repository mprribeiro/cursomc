package com.marcosribeiro.services;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcosribeiro.domain.Address;
import com.marcosribeiro.domain.City;
import com.marcosribeiro.domain.Client;
import com.marcosribeiro.domain.enums.ClientType;
import com.marcosribeiro.domain.enums.Profile;
import com.marcosribeiro.dto.ClientDTO;
import com.marcosribeiro.dto.ClientNewDTO;
import com.marcosribeiro.repository.AddressRepository;
import com.marcosribeiro.repository.ClientRepository;
import com.marcosribeiro.security.UserSS;
import com.marcosribeiro.services.exceptions.AuthorizationException;
import com.marcosribeiro.services.exceptions.DataIntegrityException;
import com.marcosribeiro.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Client find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Access denied!");
		}
		Optional<Client> obj = clientRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id));
	}
	
	public List<Client> findAll() {
		List<Client> obj = clientRepository.findAll();
		return obj;
	}
	
	public Client findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Profile.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Access denied!");
		}
		Client client = clientRepository.findByEmail(email);
		if (client == null) {
			throw new ObjectNotFoundException("Object not found! Id: " + user.getId() + ", Type: " + Client.class.getName());
		}
		return client;
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			clientRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It's not possible to delete because there are entities related to!");
		}
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clientRepository.findAll(pageRequest);
	}
	
	public Client update(Client client) {
		Client newClient = find(client.getId());
		updateData(newClient, client);
		return clientRepository.save(newClient);
	}
	
	public Client fromDTO(ClientDTO clientDTO) {
		byte[] image = Base64.getDecoder().decode(clientDTO.getClientImg());
		return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null, null, null, image);
	}
	
	public Client fromDTO(ClientNewDTO clientNewDTO) {
		byte[] image = Base64.getDecoder().decode(clientNewDTO.getClientImg());
		Client client = new Client(null, clientNewDTO.getName(), clientNewDTO.getEmail(), 
				clientNewDTO.getRegister(), ClientType.toEnum(clientNewDTO.getType()), pe.encode(clientNewDTO.getPassword()), image);
		City city = new City(clientNewDTO.getCityID(), null, null);
		Address address =new Address(null, clientNewDTO.getStreet(), clientNewDTO.getNumber(), clientNewDTO.getComplement(),
				clientNewDTO.getNeighborhood(), clientNewDTO.getCep(), client, city);
		client.getAddresses().add(address);
		client.getPhones().add(clientNewDTO.getPhone1());
		if (clientNewDTO.getPhone2() != null) {
			client.getPhones().add(clientNewDTO.getPhone2());
		}
		if (clientNewDTO.getPhone3() != null) {
			client.getPhones().add(clientNewDTO.getPhone3());
		}
		return client;
	}
	
	public void updateData(Client newClient, Client client) {
		newClient.setName(client.getName());
		newClient.setEmail(client.getEmail());
		newClient.setClientImg(client.getClientImg());
	}
	
	@Transactional
	public Client insert(Client client) {
		client.setId(null);
		client = clientRepository.save(client);
		addressRepository.saveAll(client.getAddresses());
		return client;
	}
}
