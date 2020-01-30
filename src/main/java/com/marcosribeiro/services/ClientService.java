package com.marcosribeiro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.marcosribeiro.domain.Client;
import com.marcosribeiro.dto.ClientDTO;
import com.marcosribeiro.repository.ClientRepository;
import com.marcosribeiro.services.exceptions.DataIntegrityException;
import com.marcosribeiro.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	public Client find(Integer id) {
		Optional<Client> obj = clientRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));
		}
	
	public List<Client> findAll() {
		List<Client> obj = clientRepository.findAll();
		return obj;
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			clientRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It's not possible!");
		}
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clientRepository.findAll(pageRequest);
	}
	
	public Client update(Client client) {
		find(client.getId());
		return clientRepository.save(client);
	}
	
	public Client fromDTO(ClientDTO clientDTO) {
		throw new UnsupportedOperationException();
	}
}
