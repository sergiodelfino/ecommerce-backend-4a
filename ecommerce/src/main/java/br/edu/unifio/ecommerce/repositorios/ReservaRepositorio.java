package br.edu.unifio.ecommerce.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.unifio.ecommerce.entidades.Reserva;

public interface ReservaRepositorio extends JpaRepository<Reserva, Long> {

    boolean existsByUsuarioIdAndViagemIdAndStatus(
        Long usuarioId, Long viagemId, String status);

    long countByViagemIdAndStatus(Long viagemId, String status);

    List<Reserva> findByViagemId(Long viagemId);
}
