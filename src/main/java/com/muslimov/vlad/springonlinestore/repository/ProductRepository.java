package com.muslimov.vlad.springonlinestore.repository;

import com.muslimov.vlad.springonlinestore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
