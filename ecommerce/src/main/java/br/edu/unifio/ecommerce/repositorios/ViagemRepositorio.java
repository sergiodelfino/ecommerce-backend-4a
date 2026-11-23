package br.edu.unifio.ecommerce.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.unifio.ecommerce.entidades.Viagem;

public interface ViagemRepositorio extends JpaRepository<Viagem, Long> {
}
