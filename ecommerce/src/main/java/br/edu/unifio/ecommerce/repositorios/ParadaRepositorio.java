package br.edu.unifio.ecommerce.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.unifio.ecommerce.entidades.Parada;

public interface ParadaRepositorio extends JpaRepository<Parada, Long> {
}
