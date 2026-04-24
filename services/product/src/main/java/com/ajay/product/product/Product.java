package com.ajay.product.product;

import com.ajay.product.category.Category.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Product {
    @Id
    private Integer id;
    private String name;
    private String description;
    private double availability;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
}
