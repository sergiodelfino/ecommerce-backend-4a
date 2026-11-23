package br.edu.unifio.ecommerce.recursos;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import br.edu.unifio.ecommerce.entidades.Aviso;
import br.edu.unifio.ecommerce.servicos.AvisoServico;

@RestController
@RequestMapping("/avisos")
public class AvisoRecurso {
    private final AvisoServico servico;

    public AvisoRecurso(AvisoServico servico) {
        this.servico = servico;
    }

    @GetMapping
    public List<Aviso> listar() {
        return servico.listar();
    }

    @GetMapping("/{id}")
    public Aviso buscarPorId(@PathVariable Long id) {
        return servico.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aviso cadastrar(@Valid @RequestBody Aviso dados) {
        return servico.cadastrar(dados);
    }

    @PutMapping("/{id}")
    public Aviso atualizar(@PathVariable Long id,
                                 @Valid @RequestBody Aviso dados) {
        return servico.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        servico.excluir(id);
    }
}
