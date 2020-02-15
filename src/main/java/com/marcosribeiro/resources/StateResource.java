package com.marcosribeiro.resources;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marcosribeiro.domain.City;
import com.marcosribeiro.domain.State;
import com.marcosribeiro.dto.CityDTO;
import com.marcosribeiro.dto.StateDTO;
import com.marcosribeiro.services.CityService;
import com.marcosribeiro.services.StateService;

@RestController
@RequestMapping(value="/states")
public class StateResource {

	@Autowired
	private StateService stateService;
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<StateDTO>> findAll() {
		List<State> list = stateService.findAll();
		List<StateDTO> listDTO= list.stream().map(obj -> new StateDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/{id}/cities", method=RequestMethod.GET)
	public ResponseEntity<List<CityDTO>> find(@PathVariable Integer id) {
		List<City> list = cityService.findByState(id);
		List<CityDTO> listDTO= list.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
}
