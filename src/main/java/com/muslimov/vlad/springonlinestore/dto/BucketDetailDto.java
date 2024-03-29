package com.muslimov.vlad.springonlinestore.dto;

import com.muslimov.vlad.springonlinestore.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketDetailDto {
    private String title;
    private Long productId;
    private BigDecimal price;
    private BigDecimal amount;
    private BigDecimal sum;

    public BucketDetailDto(Product product) {
        this.title = product.getTitle();
        this.productId = product.getId();
        this.price = product.getPrice();
        this.amount = new BigDecimal("1.0");
        this.sum = product.getPrice();
    }
}
