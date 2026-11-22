package br.edu.unifio.ecommerce.servicos;

import java.util.List;
import org.springframework.stereotype.Service;
import br.edu.unifio.ecommerce.entidades.Rota;
import br.edu.unifio.ecommerce.repositorios.RotaRepositorio;
import br.edu.unifio.ecommerce.excecoes.RecursoNaoEncontradoException;

@Service
public class RotaServico {
    private final RotaRepositorio repositorio;

    public RotaServico(RotaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Rota> listar() {
        return repositorio.findAll();
    }

    public Rota buscarPorId(Long id) {
        return repositorio.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Rota não encontrada"));
    }

    public Rota cadastrar(Rota dados) {
        dados.setId(null);
        return repositorio.save(dados);
    }

    public Rota atualizar(Long id, Rota dados) {
        buscarPorId(id);
        dados.setId(id);
        return repositorio.save(dados);
    }

    public void excluir(Long id) {
        repositorio.delete(buscarPorId(id));
    }
}
