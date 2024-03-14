package com.muslimov.vlad.springonlinestore.service;

import com.muslimov.vlad.springonlinestore.dto.BucketDetailDto;
import com.muslimov.vlad.springonlinestore.dto.BucketDto;
import com.muslimov.vlad.springonlinestore.model.*;
import com.muslimov.vlad.springonlinestore.repository.BucketRepository;
import com.muslimov.vlad.springonlinestore.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService {

    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final OrderService orderService;

    @Override
    @Transactional
    public Bucket createBucket(User user, List<Long> productIds) {

        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Product> productList = getCollectRefProductsByIds(productIds);
        bucket.setProducts(productList);
        return bucketRepository.save(bucket);
    }

    private List<Product> getCollectRefProductsByIds(List<Long> productIds) {

        return productIds.stream()
            .map(productRepository::getReferenceById)
            .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void addProducts(Bucket bucket, List<Long> productIds) {

        List<Product> products = bucket.getProducts();
        List<Product> newProductsList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductsList.addAll(getCollectRefProductsByIds(productIds));
        bucket.setProducts(newProductsList);
        bucketRepository.save(bucket);
    }

    @Override
    public BucketDto getBucketByUser(Long userId) {
        User user = userService.findByIdOrThrow(userId);

        if (user.getBucket() == null) {
            return new BucketDto();
        }

        BucketDto bucketDto = new BucketDto();
        Map<Long, BucketDetailDto> mapByProductId = new HashMap<>();

        List<Product> products = user.getBucket().getProducts();
        for (Product product : products) {
            BucketDetailDto detail = mapByProductId.get(product.getId());
            if (detail == null) {
                mapByProductId.put(product.getId(), new BucketDetailDto(product));
            } else {
                detail.setAmount(detail.getAmount().add(new BigDecimal("1.0")));
                detail.setSum(detail.getSum().add(product.getPrice()));
            }
        }

        bucketDto.setBucketDetails(new ArrayList<>(mapByProductId.values()));
        bucketDto.aggregate();

        return bucketDto;
    }

    @Override
    @Transactional
    public void commitBucketToOrder(Long userId) {
        User user = userService.findByIdOrThrow(userId);

        Bucket bucket = user.getBucket();
        if (bucket == null || bucket.getProducts().isEmpty()) {
            return;
        }

        Order order = new Order();
        order.setStatus(OrderStatus.NEW);
        order.setUser(user);

        Map<Product, Long> productWithAmount = bucket.getProducts().stream()
            .collect(Collectors.groupingBy(product -> product, Collectors.counting()));

        List<OrderDetails> orderDetails = productWithAmount.entrySet().stream()
            .map(pair -> new OrderDetails(order, pair.getKey(), pair.getValue()))
            .collect(Collectors.toList());

        BigDecimal total = BigDecimal.valueOf(orderDetails.stream()
            .map(detail -> detail.getPrice().multiply(detail.getAmount()))
            .mapToDouble(BigDecimal::doubleValue).sum());

        order.setDetails(orderDetails);
        order.setSum(total);
        order.setAddress("none");

        orderService.saveOrder(order);
        bucket.getProducts().clear();
        bucketRepository.save(bucket);
    }
}
