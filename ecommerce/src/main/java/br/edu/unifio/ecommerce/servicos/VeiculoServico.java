package br.edu.unifio.ecommerce.servicos;

import java.util.List;
import br.edu.unifio.ecommerce.excecoes.RecursoNaoEncontradoException;
import br.edu.unifio.ecommerce.excecoes.ConflitoException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.unifio.ecommerce.entidades.Veiculo;
import br.edu.unifio.ecommerce.repositorios.VeiculoRepositorio;

@Service
public class VeiculoServico {

    private final VeiculoRepositorio repositorio;

    public VeiculoServico(VeiculoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Veiculo> listar() {
        return repositorio.findAll();
    }

    public Veiculo buscarPorId(Long id) {
        return repositorio.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Veículo não encontrado"));
    }

    public Veiculo cadastrar(Veiculo veiculo) {
        validarCapacidade(veiculo.getCapacidade());

        if (repositorio.existsByPlaca(veiculo.getPlaca())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Placa já cadastrada");
        }

        veiculo.setId(null);
        return repositorio.save(veiculo);
    }

    public Veiculo atualizar(Long id, Veiculo dados) {
        Veiculo veiculo = buscarPorId(id);

        validarCapacidade(dados.getCapacidade());

        if (!veiculo.getPlaca().equals(dados.getPlaca())
                && repositorio.existsByPlaca(dados.getPlaca())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Placa já cadastrada");
        }

        veiculo.setPlaca(dados.getPlaca());
        veiculo.setModelo(dados.getModelo());
        veiculo.setTipo(dados.getTipo());
        veiculo.setCapacidade(dados.getCapacidade());

        return repositorio.save(veiculo);
    }

    public void excluir(Long id) {
        Veiculo veiculo = buscarPorId(id);
        repositorio.delete(veiculo);
    }

    private void validarCapacidade(Integer capacidade) {
        if (capacidade == null || capacidade <= 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "A capacidade deve ser maior que zero");
        }
    }
}
