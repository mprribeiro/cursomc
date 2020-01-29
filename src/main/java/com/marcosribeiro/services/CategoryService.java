package com.marcosribeiro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.marcosribeiro.domain.Category;
import com.marcosribeiro.dto.CategoryDTO;
import com.marcosribeiro.repository.CategoryRepository;
import com.marcosribeiro.services.exceptions.DataIntegrityException;
import com.marcosribeiro.services.exceptions.ObjectNotFoundException;

import org.springframework.data.domain.Sort.Direction;

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
		return categoryRepository.findAll();
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
		try {
			categoryRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It's not possible!");
		}
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoryRepository.findAll(pageRequest);
	}
	
	public Category fromDTO(CategoryDTO categoryDTO) {
		return new Category(categoryDTO.getId(), categoryDTO.getName());
	}

}
