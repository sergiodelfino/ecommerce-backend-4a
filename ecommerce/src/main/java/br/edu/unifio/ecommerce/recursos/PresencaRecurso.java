package br.edu.unifio.ecommerce.recursos;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import br.edu.unifio.ecommerce.entidades.Presenca;
import br.edu.unifio.ecommerce.servicos.PresencaServico;

@RestController
@RequestMapping("/presencas")
public class PresencaRecurso {
    private final PresencaServico servico;

    public PresencaRecurso(PresencaServico servico) {
        this.servico = servico;
    }

    @GetMapping
    public List<Presenca> listar() {
        return servico.listar();
    }

    @GetMapping("/{id}")
    public Presenca buscarPorId(@PathVariable Long id) {
        return servico.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Presenca cadastrar(@Valid @RequestBody Presenca dados) {
        return servico.cadastrar(dados);
    }

    @PutMapping("/{id}")
    public Presenca atualizar(@PathVariable Long id,
                                 @Valid @RequestBody Presenca dados) {
        return servico.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        servico.excluir(id);
    }
}
