package com.muslimov.vlad.springonlinestore.service;

import com.muslimov.vlad.springonlinestore.model.Order;
import com.muslimov.vlad.springonlinestore.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceIml implements OrderService {

    private final OrderRepository orderRepository;


    @Override
    @Transactional
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
