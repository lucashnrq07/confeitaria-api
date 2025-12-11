package com.lucas.confeitaria_api.client.entities;

import com.lucas.confeitaria_api.admin.entities.CakeSize;
import com.lucas.confeitaria_api.admin.entities.RecipeType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_public_orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class PublicOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cake_size_id")
    private CakeSize size;

    @Enumerated(EnumType.STRING)
    private RecipeType type; // tipo de recheio (BRIGADEIRO, MOUSSE)

    private String chosenRecheio; // sabor do recheio
    private String chosenMassa;    // sabor da massa

    private BigDecimal finalPrice; // preço calculado
}

