package com.muslimov.vlad.springonlinestore.dto;

import java.math.BigDecimal;

public record ProductDto(Long id, String title, BigDecimal price) {
}
