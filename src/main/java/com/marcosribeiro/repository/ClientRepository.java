package com.marcosribeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.marcosribeiro.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository <Client, Integer> {

	@Transactional(readOnly=true)
	Client findByEmail(String email);
}
