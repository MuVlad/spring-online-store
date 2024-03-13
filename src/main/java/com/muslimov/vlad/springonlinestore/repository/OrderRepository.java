package com.muslimov.vlad.springonlinestore.repository;

import com.muslimov.vlad.springonlinestore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
