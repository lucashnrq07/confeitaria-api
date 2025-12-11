package com.lucas.confeitaria_api.product.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_recipe_usages")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class RecipeUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cake_size_id")
    private CakeSize size;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    private BigDecimal quantityUsed;
}
