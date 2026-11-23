package br.edu.unifio.ecommerce.servicos;

import java.util.List;
import org.springframework.stereotype.Service;
import br.edu.unifio.ecommerce.entidades.Parada;
import br.edu.unifio.ecommerce.repositorios.ParadaRepositorio;
import br.edu.unifio.ecommerce.excecoes.RecursoNaoEncontradoException;

@Service
public class ParadaServico {
    private final ParadaRepositorio repositorio;

    public ParadaServico(ParadaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Parada> listar() {
        return repositorio.findAll();
    }

    public Parada buscarPorId(Long id) {
        return repositorio.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Parada não encontrada"));
    }

    public Parada cadastrar(Parada dados) {
        dados.setId(null);
        return repositorio.save(dados);
    }

    public Parada atualizar(Long id, Parada dados) {
        buscarPorId(id);
        dados.setId(id);
        return repositorio.save(dados);
    }

    public void excluir(Long id) {
        repositorio.delete(buscarPorId(id));
    }
}
