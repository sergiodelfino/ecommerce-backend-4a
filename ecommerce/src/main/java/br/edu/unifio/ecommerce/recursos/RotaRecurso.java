package br.edu.unifio.ecommerce.recursos;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import br.edu.unifio.ecommerce.entidades.Rota;
import br.edu.unifio.ecommerce.servicos.RotaServico;

@RestController
@RequestMapping("/rotas")
public class RotaRecurso {
    private final RotaServico servico;

    public RotaRecurso(RotaServico servico) {
        this.servico = servico;
    }

    @GetMapping
    public List<Rota> listar() {
        return servico.listar();
    }

    @GetMapping("/{id}")
    public Rota buscarPorId(@PathVariable Long id) {
        return servico.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Rota cadastrar(@Valid @RequestBody Rota dados) {
        return servico.cadastrar(dados);
    }

    @PutMapping("/{id}")
    public Rota atualizar(@PathVariable Long id,
                                  @Valid @RequestBody Rota dados) {
        return servico.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        servico.excluir(id);
    }
}
