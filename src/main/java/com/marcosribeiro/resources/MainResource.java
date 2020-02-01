package com.marcosribeiro.resources;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class MainResource {

	@RequestMapping(method=RequestMethod.GET)
	public String Main( ) {
		return "Aplicação para aplicar conhecimentos de DevOps, Java 8, Spring e Angular.";
	}
			
}
