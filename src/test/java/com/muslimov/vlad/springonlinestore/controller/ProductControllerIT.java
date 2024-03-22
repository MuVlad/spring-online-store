package com.muslimov.vlad.springonlinestore.controller;

import com.muslimov.vlad.springonlinestore.BaseIntegrationTest;
import com.muslimov.vlad.springonlinestore.model.Product;
import com.muslimov.vlad.springonlinestore.model.User;
import com.muslimov.vlad.springonlinestore.repository.ProductRepository;
import com.muslimov.vlad.springonlinestore.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@DisplayName("Endpoint Product")
class ProductControllerIT extends BaseIntegrationTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Поиск продуктов")
    void getProducts() throws Exception {
        //given
        final String expectedBody = """
            [
              {
                "title": "Cheese",
                "price": 450.00
              },
              {
                "title": "Beer",
                "price": 45.00
              },
              {
                "title": "Milk",
                "price": 65.00
              },
              {
                "title": "Tomato",
                "price": 115.00
              },
              {
                "title": "Bread",
                "price": 58.00
              }
            ]
            """;

        //when
        mockMvc.perform(get("/api/v1/products"))
            //then
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                content().json(expectedBody)
            );
    }

    @Test
    @DisplayName("Поиск продуктоа по id")
    void getProductByID() throws Exception {
        //given
        final String expectedBody = """
            {
                "title": "Cheese",
                "price": 450.00
              }
            """;

        //when
        mockMvc.perform(get("/api/v1/products/1"))
            //then
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                content().json(expectedBody)
            );
    }

    @Test
    @Transactional
    @DisplayName("Создание нового продукта")
    void createProduct() throws Exception {
        //given
        productRepository.save(
            Product.builder()
                .title("Some product")
                .price(new BigDecimal(500))
                .build()
        );

        final String expectedBody = """
            {
                "title": "Some product",
                "price": 500.00
              }
            """;

        //when
        mockMvc.perform(post("/api/v1/products")
                .content(expectedBody)
                .contentType(MediaType.APPLICATION_JSON)
            )
            //then
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                content().json(expectedBody)
            );
    }

    @Test
    @Transactional
    @DisplayName("Добавления продукта в корзину пользователя")
    void addProductToBucket() throws Exception {
        //given
        final Long productId = 1L;
        final Long userId = 1L;
        final User user = userRepository.findByIdOrThrow(userId);

        //when
        mockMvc.perform(post("/api/v1/products/" + productId + "/bucket")
                .param("userId", userId.toString())
            )
            //then
            .andExpect(status().isNoContent());

        final var bucket = user.getBucket();
        assertTrue(bucket.getProducts().contains(productRepository.findById(productId).orElseThrow()));
    }
}