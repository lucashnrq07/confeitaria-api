package com.lucas.confeitaria_api.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Product implements Serializable {
    private static final long serieVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    private BigDecimal basePrice;

    @OneToMany(mappedBy = "product")
    private List<ProductOption> options = new ArrayList<>();

    public void addOption(ProductOption option) {
        option.setProduct(this);
        this.options.add(option);
    }
}
