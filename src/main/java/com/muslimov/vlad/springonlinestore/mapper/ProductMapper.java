package com.muslimov.vlad.springonlinestore.mapper;

import com.muslimov.vlad.springonlinestore.dto.ProductDto;
import com.muslimov.vlad.springonlinestore.model.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductMapper {

    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);
}
