package com.muslimov.vlad.springonlinestore.mapper;

import com.muslimov.vlad.springonlinestore.dto.ProductDto;
import com.muslimov.vlad.springonlinestore.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    ProductMapper productMapper = new ProductMapperImpl();

    @Test
    void toDto() {
        //given
        Product product = Product.builder()
            .id(100L)
            .title("Some product")
            .price(new BigDecimal(500))
            .build();

        ProductDto expected = new ProductDto(
            "Some product",
            new BigDecimal(500)
        );

        //when
        final var actual = productMapper.toDto(product);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void toEntity() {
        //given
        Product expected = Product.builder()
            .title("Some product")
            .price(new BigDecimal(500))
            .build();

        ProductDto productDto = new ProductDto(
            "Some product",
            new BigDecimal(500)
        );

        //when
        final var actual = productMapper.toEntity(productDto);

        //then
        assertEquals(expected, actual);
    }
}