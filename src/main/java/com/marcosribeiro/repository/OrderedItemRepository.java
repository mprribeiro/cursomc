package com.marcosribeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcosribeiro.domain.OrderedItem;

@Repository
public interface OrderedItemRepository extends JpaRepository <OrderedItem, Integer> {

}
