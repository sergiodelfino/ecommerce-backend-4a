package br.edu.unifio.ecommerce.servicos;

import java.util.List;
import org.springframework.stereotype.Service;
import br.edu.unifio.ecommerce.entidades.Viagem;
import br.edu.unifio.ecommerce.repositorios.ViagemRepositorio;
import br.edu.unifio.ecommerce.excecoes.RecursoNaoEncontradoException;

@Service
public class ViagemServico {
    private final ViagemRepositorio repositorio;

    public ViagemServico(ViagemRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Viagem> listar() {
        return repositorio.findAll();
    }

    public Viagem buscarPorId(Long id) {
        return repositorio.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Viagem não encontrada"));
    }

    public Viagem cadastrar(Viagem dados) {
        dados.setId(null);
        return repositorio.save(dados);
    }

    public Viagem atualizar(Long id, Viagem dados) {
        buscarPorId(id);
        dados.setId(id);
        return repositorio.save(dados);
    }

    public void excluir(Long id) {
        repositorio.delete(buscarPorId(id));
    }
}
