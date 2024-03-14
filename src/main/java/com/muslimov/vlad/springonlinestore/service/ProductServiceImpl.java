package com.muslimov.vlad.springonlinestore.service;

import com.muslimov.vlad.springonlinestore.dto.ProductDto;
import com.muslimov.vlad.springonlinestore.exception.model.NotFoundException;
import com.muslimov.vlad.springonlinestore.mapper.ProductMapper;
import com.muslimov.vlad.springonlinestore.model.Bucket;
import com.muslimov.vlad.springonlinestore.model.Product;
import com.muslimov.vlad.springonlinestore.model.User;
import com.muslimov.vlad.springonlinestore.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserService userService;
    private final BucketService bucketService;

    @Override
    public List<ProductDto> getAll() {

        return productRepository
            .findAll()
            .stream()
            .map(productMapper::toDto)
            .toList();
    }

    @Override
    public void addToUserBucket(Long productId, Long userId) {
        User user = userService.findByIdOrThrow(userId);

        Bucket bucket = user.getBucket();
        if (bucket == null) {
            Bucket newBucket = bucketService.createBucket(user, Collections.singletonList(productId));
            user.setBucket(newBucket);
            userService.save(user);
        } else {
            bucketService.addProducts(bucket, Collections.singletonList(productId));
        }
    }

    @Override
    @Transactional
    public ProductDto addProduct(ProductDto productDto) {
        final Product product = productMapper.toEntity(productDto);
        productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public ProductDto getById(Long id) {

        Product product = productRepository.findById(id).orElseThrow(
            () -> new NotFoundException("Продукта с id:" + id + " не найдено!")
        );
        return productMapper.toDto(product);
    }
}
