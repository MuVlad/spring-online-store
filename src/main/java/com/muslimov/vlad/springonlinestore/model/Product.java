package com.muslimov.vlad.springonlinestore.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "products")
public class Product {

    private static final String SEQ_NAME = "product_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(
        name = SEQ_NAME,
        sequenceName = SEQ_NAME,
        initialValue = 3,
        allocationSize = 1
    )
    private Long id;
    private String title;
    private BigDecimal price;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "products_categories",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;
}