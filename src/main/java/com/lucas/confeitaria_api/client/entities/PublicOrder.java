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

    // ⚠️ não pode ser CakeSize diretamente sem @ManyToOne
    @ManyToOne
    @JoinColumn(name = "cake_size_id")
    private CakeSize size;   // renomeei para size, não sizeId

    @Enumerated(EnumType.STRING)
    private RecipeType type; // tipo de recheio (BRIGADEIRO, MOUSSE)

    private String chosenRecheio; // sabor do recheio (visual)
    private String chosenMassa;    // sabor da massa (visual)

    private BigDecimal finalPrice; // preço calculado
}

