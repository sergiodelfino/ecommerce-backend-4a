package br.edu.unifio.ecommerce.recursos;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.edu.unifio.ecommerce.entidades.Usuario;
import br.edu.unifio.ecommerce.servicos.UsuarioServico;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRecurso {

    private final UsuarioServico servico;

    public UsuarioRecurso(UsuarioServico servico) {
        this.servico = servico;
    }

    @GetMapping
    public List<Usuario> listar() {
        return servico.listar();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return servico.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario cadastrar(@Valid @RequestBody Usuario dados) {
        return servico.cadastrar(dados);
    }

    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id,
                             @Valid @RequestBody Usuario dados) {
        return servico.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        servico.excluir(id);
    }
}
