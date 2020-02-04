package com.marcosribeiro.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.marcosribeiro.domain.Category;
import com.marcosribeiro.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository <Product, Integer>{

	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat WHERE obj.name LIKE %:name% AND cat in :categories")
	Page<Product> search(@Param("name") String name, @Param("categories") List<Category>categories, Pageable pageRequest);

	// Page<Product> findDistinctByNameContainingAndCategoriesIn(String name, List<Category>categories, Pageable pageRequest);

}
