package com.muslimov.vlad.springonlinestore.controller;

import com.muslimov.vlad.springonlinestore.dto.BucketDto;
import com.muslimov.vlad.springonlinestore.service.BucketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/buckets")
@Tag(name = "Корзина", description = "Контроллер для работы с корзиной")
public class BucketController {

    private final BucketService bucketService;

    @GetMapping
    @Operation(summary = "Получение корзины")
    public HttpEntity<BucketDto> getBucketByUser(@RequestParam Long userId) {

        return ResponseEntity.ok(bucketService.getBucketByUser(userId));
    }

    @PostMapping
    @Operation(summary = "Формирование заказа, добавление корзины к заказу")
    public HttpEntity<?> commitBucket(@RequestParam Long userId) {

        bucketService.commitBucketToOrder(userId);
        return ResponseEntity.noContent().build();
    }
}
