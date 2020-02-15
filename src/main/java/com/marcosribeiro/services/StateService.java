package com.marcosribeiro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcosribeiro.domain.State;
import com.marcosribeiro.repository.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;
	
		
	public List<State> findAll() {
		return stateRepository.findAllByOrderByName();
	}

}
