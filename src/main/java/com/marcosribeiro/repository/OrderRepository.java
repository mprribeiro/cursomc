package com.marcosribeiro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcosribeiro.domain.Client;
import com.marcosribeiro.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository <Order, Integer> {

	Page<Order> findByClient(Client client, Pageable pageRequest);
}
