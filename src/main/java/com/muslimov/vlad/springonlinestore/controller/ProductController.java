package com.muslimov.vlad.springonlinestore.controller;

import com.muslimov.vlad.springonlinestore.dto.ProductDto;
import com.muslimov.vlad.springonlinestore.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Tag(name = "Товары", description = "Контроллер для работы с товарами")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @Operation(summary = "Получение списка продуктов")
    public HttpEntity<List<ProductDto>> getProducts() {

        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение продукта по id")
    public HttpEntity<ProductDto> getProductByID(@PathVariable Long id) {

        return ResponseEntity.ok(productService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Создание нового продукта")
    public HttpEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.addProduct(productDto));
    }

    @PostMapping("/{id}/bucket")
    @Operation(summary = "Добавление продукта в корзину")
    public HttpEntity<?> addProductToBucket(
        @PathVariable Long id,
        @RequestParam Long userId
    ) {

        productService.addToUserBucket(id, userId);
        return ResponseEntity.noContent().build();
    }
}
