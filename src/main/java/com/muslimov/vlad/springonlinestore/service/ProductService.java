package com.muslimov.vlad.springonlinestore.service;

import com.muslimov.vlad.springonlinestore.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();

    void addToUserBucket(Long productId, Long userId);

    ProductDto addProduct(ProductDto productDto);

    ProductDto getById(Long id);
}
