package br.edu.unifio.ecommerce.recursos;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.edu.unifio.ecommerce.entidades.Reserva;
import br.edu.unifio.ecommerce.servicos.ReservaServico;

@RestController
@RequestMapping("/reservas")
public class ReservaRecurso {

    private final ReservaServico servico;

    public ReservaRecurso(ReservaServico servico) {
        this.servico = servico;
    }

    @GetMapping
    public List<Reserva> listar() {
        return servico.listar();
    }

    @GetMapping("/{id}")
    public Reserva buscarPorId(@PathVariable Long id) {
        return servico.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reserva cadastrar(@Valid @RequestBody Reserva reserva) {
        return servico.cadastrar(reserva);
    }

    @PutMapping("/{id}")
    public Reserva atualizar(
            @PathVariable Long id,
            @RequestBody Reserva dados) {
        return servico.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        servico.excluir(id);
    }
}
