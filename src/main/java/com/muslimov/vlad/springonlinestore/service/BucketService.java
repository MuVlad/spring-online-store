package com.muslimov.vlad.springonlinestore.service;

import com.muslimov.vlad.springonlinestore.dto.BucketDto;
import com.muslimov.vlad.springonlinestore.model.Bucket;
import com.muslimov.vlad.springonlinestore.model.User;

import java.util.List;

public interface BucketService {

    Bucket createBucket(User user, List<Long> productIds);

    void addProducts(Bucket bucket, List<Long> productIds);

    BucketDto getBucketByUser(Long userId);

    void commitBucketToOrder(Long userId);
}
