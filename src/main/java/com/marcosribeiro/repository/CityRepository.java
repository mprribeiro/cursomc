package com.marcosribeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.marcosribeiro.domain.City;

@Repository
public interface CityRepository extends JpaRepository <City, Integer> {

	@Transactional(readOnly=true)
	public List<City> findByStateIdOrderByName(Integer id);
}
