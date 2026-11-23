package br.edu.unifio.ecommerce.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Parada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Min(1)
    private Integer ordem;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "rota_id")
    private Rota rota;
}
