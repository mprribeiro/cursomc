package com.marcosribeiro.services;

import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.marcosribeiro.domain.Client;
import com.marcosribeiro.repository.ClientRepository;
import com.marcosribeiro.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	private Random random = new Random();

	public void sendNewPassword(String email) {
		Client client = clientRepository.findByEmail(email);
		if (client == null) {
			throw new ObjectNotFoundException("Email not found!");
		}
		
		String newPassword = newPassword();
		client.setPassword(pe.encode(newPassword));
		clientRepository.saveAll(Arrays.asList(client));
		
		emailService.sendNewPasswordEmail(client, newPassword);
		
	}

	private String newPassword() {
		char vet[] = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		// 0 = 0 a 9; 1 = letra maiúscula; 2 = letra minúscula
		int opt = random.nextInt(3);
		if (opt == 0) {
			return (char) (random.nextInt(10) + 48);
		} else if (opt == 1) {
			return (char) (random.nextInt(26) + 65);
		} else {
			return (char) (random.nextInt(26) + 97);
		}
	}
}
