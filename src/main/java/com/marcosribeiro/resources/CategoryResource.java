package com.marcosribeiro.resources;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marcosribeiro.domain.Category;
import com.marcosribeiro.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public List<Category> retrieveAll() {
		return categoryService.findAll();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> retrieve(@PathVariable Integer id) {
		Category obj = categoryService.find(id);
		return ResponseEntity.ok(obj);
	}
			
}
