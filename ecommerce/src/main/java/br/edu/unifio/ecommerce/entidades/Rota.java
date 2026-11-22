package br.edu.unifio.ecommerce.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String origem;

    @NotBlank
    @Column(nullable = false)
    private String destino;

    @NotNull
    @Min(1)
    private Integer duracaoMinutos;
}
