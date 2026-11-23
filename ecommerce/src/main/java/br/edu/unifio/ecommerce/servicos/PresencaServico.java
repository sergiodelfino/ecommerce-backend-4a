package br.edu.unifio.ecommerce.servicos;

import java.util.List;
import org.springframework.stereotype.Service;
import br.edu.unifio.ecommerce.entidades.Presenca;
import br.edu.unifio.ecommerce.repositorios.PresencaRepositorio;
import br.edu.unifio.ecommerce.excecoes.RecursoNaoEncontradoException;

@Service
public class PresencaServico {
    private final PresencaRepositorio repositorio;

    public PresencaServico(PresencaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Presenca> listar() {
        return repositorio.findAll();
    }

    public Presenca buscarPorId(Long id) {
        return repositorio.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Presenca não encontrado"));
    }

    public Presenca cadastrar(Presenca dados) {
        dados.setId(null);
        return repositorio.save(dados);
    }

    public Presenca atualizar(Long id, Presenca dados) {
        buscarPorId(id);
        dados.setId(id);
        return repositorio.save(dados);
    }

    public void excluir(Long id) {
        repositorio.delete(buscarPorId(id));
    }
}
