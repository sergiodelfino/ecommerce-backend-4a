package br.edu.unifio.ecommerce.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Presenca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne(optional = false)
    @JoinColumn(name = "reserva_id", unique = true)
    private Reserva reserva;

    @NotNull
    private Boolean presente;
}
