package com.ajay.product.category.Category;

import com.ajay.product.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Category {
    @Id
    private Integer id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
