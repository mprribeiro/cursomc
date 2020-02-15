package com.marcosribeiro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcosribeiro.domain.City;
import com.marcosribeiro.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
		
	public List<City> findByState(Integer id) {
		List<City> list = cityRepository.findByStateIdOrderByName(id);
		return list;
	}

}
