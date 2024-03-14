package com.muslimov.vlad.springonlinestore.service;

import com.muslimov.vlad.springonlinestore.model.Order;

public interface OrderService {

    void saveOrder(Order order);

    Order getOrder(Long id);
}
