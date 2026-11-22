package br.edu.unifio.ecommerce.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.unifio.ecommerce.entidades.Veiculo;

public interface VeiculoRepositorio extends JpaRepository<Veiculo, Long> {

    boolean existsByPlaca(String placa);

}
