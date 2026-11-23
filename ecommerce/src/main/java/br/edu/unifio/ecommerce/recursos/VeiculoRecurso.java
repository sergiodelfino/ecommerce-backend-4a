package br.edu.unifio.ecommerce.recursos;

import java.util.List;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unifio.ecommerce.entidades.Veiculo;
import br.edu.unifio.ecommerce.servicos.VeiculoServico;

@RestController
@RequestMapping("/veiculos")
public class VeiculoRecurso {

    private final VeiculoServico servico;

    public VeiculoRecurso(VeiculoServico servico) {
        this.servico = servico;
    }

    @GetMapping
    public List<Veiculo> listar() {
        return servico.listar();
    }

    @GetMapping("/{id}")
    public Veiculo buscarPorId(@PathVariable Long id) {
        return servico.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo cadastrar(@Valid @RequestBody Veiculo veiculo) {
        return servico.cadastrar(veiculo);
    }

    @PutMapping("/{id}")
    public Veiculo atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Veiculo veiculo) {
        return servico.atualizar(id, veiculo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        servico.excluir(id);
    }
}
