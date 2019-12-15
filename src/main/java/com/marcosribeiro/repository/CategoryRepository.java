package com.marcosribeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcosribeiro.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository <Category, Integer> {

	
}
