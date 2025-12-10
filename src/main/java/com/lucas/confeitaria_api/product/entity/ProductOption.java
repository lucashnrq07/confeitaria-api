package com.lucas.confeitaria_api.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_options")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ProductOption implements Serializable {
    private static final long serieVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Product product;

    @Enumerated(EnumType.STRING)
    private ProductOptionType type;

    private String name; // "Brigadeiro", "Nutella", "20cm"
    private BigDecimal additionalPrice; // +4R$, +10R$, etc
}
