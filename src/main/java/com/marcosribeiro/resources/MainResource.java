package com.marcosribeiro.resources;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class MainResource {

	@RequestMapping(value="")
	public void Main( ) {
		System.out.println("Aplicação para aplicar conhecimentos de DevOps, Java 8, Spring e Angular.");
	}
			
}
