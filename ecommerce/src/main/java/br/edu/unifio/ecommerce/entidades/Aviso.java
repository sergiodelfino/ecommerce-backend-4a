package br.edu.unifio.ecommerce.entidades;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String mensagem;

    @NotNull
    @ManyToOne(optional = false)
    private Usuario autor;

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    @PrePersist
    public void definirData() {
        if (criadoEm == null) {
            criadoEm = LocalDateTime.now();
        }
    }
}
