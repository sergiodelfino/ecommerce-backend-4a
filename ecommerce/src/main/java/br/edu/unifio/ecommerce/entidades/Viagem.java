package br.edu.unifio.ecommerce.entidades;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Viagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Future
    @Column(nullable = false)
    private LocalDateTime partida;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "rota_id")
    private Rota rota;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @NotBlank
    private String status;
}
