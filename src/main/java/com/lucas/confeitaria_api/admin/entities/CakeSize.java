package com.lucas.confeitaria_api.admin.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "tb_cake_sizes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class CakeSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String size;
    private Integer servings;
    private BigDecimal basePrice;

    // Mapa de preço por tipo de recheio
    @ElementCollection
    @CollectionTable(name = "tb_cake_size_prices", joinColumns = @JoinColumn(name = "cake_size_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "price")
    private Map<RecipeType, BigDecimal> priceByFilling = new HashMap<>();

    // Método auxiliar para obter preço
    public BigDecimal getPriceFor(RecipeType type) {
        return priceByFilling.get(type);
    }
}
