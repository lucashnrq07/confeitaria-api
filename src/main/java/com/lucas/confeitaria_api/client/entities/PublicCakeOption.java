package com.lucas.confeitaria_api.client.entities;

import com.lucas.confeitaria_api.admin.entities.RecipeType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_public_cake_options")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class PublicCakeOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RecipeType type; // MASSA ou BRIGADEIRO ou MOUSSE

    private String name;      // Chocolate, Baunilha, Brigadeiro Preto...
}

