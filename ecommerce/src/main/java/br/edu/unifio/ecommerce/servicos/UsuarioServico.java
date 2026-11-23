package br.edu.unifio.ecommerce.servicos;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.unifio.ecommerce.entidades.Usuario;
import br.edu.unifio.ecommerce.excecoes.ConflitoException;
import br.edu.unifio.ecommerce.excecoes.RecursoNaoEncontradoException;
import br.edu.unifio.ecommerce.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServico {

    private final UsuarioRepositorio repositorio;
    private final PasswordEncoder encoder;

    public UsuarioServico(UsuarioRepositorio repositorio,
                          PasswordEncoder encoder) {
        this.repositorio = repositorio;
        this.encoder = encoder;
    }

    public List<Usuario> listar() {
        return repositorio.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repositorio.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException(
                "Usuário não encontrado"));
    }

    @Transactional
    public Usuario cadastrar(Usuario dados) {
        validarEmail(dados.getEmail(), null);
        validarPerfil(dados.getPerfil());

        dados.setId(null);
        dados.setSenha(encoder.encode(dados.getSenha()));

        return repositorio.save(dados);
    }

    @Transactional
    public Usuario atualizar(Long id, Usuario dados) {
        Usuario usuario = buscarPorId(id);

        validarEmail(dados.getEmail(), id);
        validarPerfil(dados.getPerfil());

        usuario.setNome(dados.getNome());
        usuario.setEmail(dados.getEmail());
        usuario.setPerfil(dados.getPerfil());
        usuario.setSenha(encoder.encode(dados.getSenha()));

        return repositorio.save(usuario);
    }

    @Transactional
    public void excluir(Long id) {
        repositorio.delete(buscarPorId(id));
    }

    private void validarEmail(String email, Long id) {
        boolean duplicado = repositorio.findAll().stream()
            .anyMatch(u -> u.getEmail().equalsIgnoreCase(email)
                && !u.getId().equals(id));

        if (duplicado) {
            throw new ConflitoException("E-mail já cadastrado");
        }
    }

    private void validarPerfil(String perfil) {
        if (!"ADMIN".equals(perfil) && !"ALUNO".equals(perfil)) {
            throw new IllegalArgumentException(
                "Perfil deve ser ADMIN ou ALUNO");
        }
    }
}
