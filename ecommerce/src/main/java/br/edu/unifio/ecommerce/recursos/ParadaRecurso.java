package br.edu.unifio.ecommerce.recursos;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import br.edu.unifio.ecommerce.entidades.Parada;
import br.edu.unifio.ecommerce.servicos.ParadaServico;

@RestController
@RequestMapping("/paradas")
public class ParadaRecurso {
    private final ParadaServico servico;

    public ParadaRecurso(ParadaServico servico) {
        this.servico = servico;
    }

    @GetMapping
    public List<Parada> listar() {
        return servico.listar();
    }

    @GetMapping("/{id}")
    public Parada buscarPorId(@PathVariable Long id) {
        return servico.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Parada cadastrar(@Valid @RequestBody Parada dados) {
        return servico.cadastrar(dados);
    }

    @PutMapping("/{id}")
    public Parada atualizar(@PathVariable Long id,
                                  @Valid @RequestBody Parada dados) {
        return servico.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        servico.excluir(id);
    }
}
