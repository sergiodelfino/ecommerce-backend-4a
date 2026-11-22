package br.edu.unifio.ecommerce.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(
    columnNames = {"usuario_id", "viagem_id"}))
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    private Usuario usuario;

    @NotNull
    @ManyToOne(optional = false)
    private Viagem viagem;

    @NotBlank
    private String status;
}
