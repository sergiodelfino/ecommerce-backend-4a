package br.edu.unifio.ecommerce.servicos;

import java.util.List;
import org.springframework.stereotype.Service;
import br.edu.unifio.ecommerce.entidades.Aviso;
import br.edu.unifio.ecommerce.repositorios.AvisoRepositorio;
import br.edu.unifio.ecommerce.excecoes.RecursoNaoEncontradoException;

@Service
public class AvisoServico {
    private final AvisoRepositorio repositorio;

    public AvisoServico(AvisoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Aviso> listar() {
        return repositorio.findAll();
    }

    public Aviso buscarPorId(Long id) {
        return repositorio.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Aviso não encontrado"));
    }

    public Aviso cadastrar(Aviso dados) {
        dados.setId(null);
        return repositorio.save(dados);
    }

    public Aviso atualizar(Long id, Aviso dados) {
        buscarPorId(id);
        dados.setId(id);
        return repositorio.save(dados);
    }

    public void excluir(Long id) {
        repositorio.delete(buscarPorId(id));
    }
}
