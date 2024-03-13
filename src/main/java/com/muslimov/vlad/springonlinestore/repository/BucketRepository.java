package com.muslimov.vlad.springonlinestore.repository;

import com.muslimov.vlad.springonlinestore.model.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketRepository extends JpaRepository<Bucket, Long> {
}
