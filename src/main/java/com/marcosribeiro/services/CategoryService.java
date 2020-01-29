package com.marcosribeiro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcosribeiro.domain.Category;
import com.marcosribeiro.repository.CategoryRepository;
import com.marcosribeiro.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category find(Integer id) {
		Optional<Category> obj = categoryRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id));
		}
	
	public List<Category> findAll() {
		List<Category> obj = categoryRepository.findAll();
		return obj;
	}
	
	public Category insert(Category category) {
		category.setId(null);
		return categoryRepository.save(category);
	}
	
	public Category update(Category category) {
		find(category.getId());
		return categoryRepository.save(category);
	}
	
	public void delete(Integer id) {
		find(id);
		categoryRepository.deleteById(id);
	}

}
