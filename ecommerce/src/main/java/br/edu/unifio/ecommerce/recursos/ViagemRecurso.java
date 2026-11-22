package br.edu.unifio.ecommerce.recursos;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import br.edu.unifio.ecommerce.entidades.Viagem;
import br.edu.unifio.ecommerce.servicos.ViagemServico;

@RestController
@RequestMapping("/viagens")
public class ViagemRecurso {
    private final ViagemServico servico;

    public ViagemRecurso(ViagemServico servico) {
        this.servico = servico;
    }

    @GetMapping
    public List<Viagem> listar() {
        return servico.listar();
    }

    @GetMapping("/{id}")
    public Viagem buscarPorId(@PathVariable Long id) {
        return servico.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viagem cadastrar(@Valid @RequestBody Viagem dados) {
        return servico.cadastrar(dados);
    }

    @PutMapping("/{id}")
    public Viagem atualizar(@PathVariable Long id,
                                  @Valid @RequestBody Viagem dados) {
        return servico.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        servico.excluir(id);
    }
}
