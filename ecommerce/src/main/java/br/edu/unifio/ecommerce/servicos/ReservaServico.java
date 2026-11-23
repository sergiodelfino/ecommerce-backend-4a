package br.edu.unifio.ecommerce.servicos;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.unifio.ecommerce.entidades.*;
import br.edu.unifio.ecommerce.repositorios.*;
import br.edu.unifio.ecommerce.excecoes.*;

@Service
public class ReservaServico {

    private final ReservaRepositorio reservas;
    private final UsuarioRepositorio usuarios;
    private final ViagemRepositorio viagens;

    public ReservaServico(
            ReservaRepositorio reservas,
            UsuarioRepositorio usuarios,
            ViagemRepositorio viagens) {
        this.reservas = reservas;
        this.usuarios = usuarios;
        this.viagens = viagens;
    }

    public List<Reserva> listar() {
        return reservas.findAll();
    }

    public Reserva buscarPorId(Long id) {
        return reservas.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException(
                "Reserva não encontrada"));
    }

    @Transactional
    public Reserva cadastrar(Reserva dados) {

        if (dados.getUsuario() == null ||
            dados.getUsuario().getId() == null ||
            dados.getViagem() == null ||
            dados.getViagem().getId() == null) {
            throw new IllegalArgumentException(
                "Usuário e viagem são obrigatórios");
        }

        Usuario usuario = usuarios.findById(dados.getUsuario().getId())
            .orElseThrow(() -> new RecursoNaoEncontradoException(
                "Usuário não encontrado"));

        Viagem viagem = viagens.findById(dados.getViagem().getId())
            .orElseThrow(() -> new RecursoNaoEncontradoException(
                "Viagem não encontrada"));

        if (reservas.existsByUsuarioIdAndViagemIdAndStatus(
                usuario.getId(), viagem.getId(), "CONFIRMADA")) {
            throw new ConflitoException(
                "Usuário já possui reserva nesta viagem");
        }

        long ocupadas = reservas.countByViagemIdAndStatus(
            viagem.getId(), "CONFIRMADA");

        if (ocupadas >= viagem.getVeiculo().getCapacidade()) {
            throw new ConflitoException(
                "Não existem vagas disponíveis nesta viagem");
        }

        dados.setId(null);
        dados.setUsuario(usuario);
        dados.setViagem(viagem);
        dados.setStatus("CONFIRMADA");

        return reservas.save(dados);
    }

    @Transactional
    public Reserva atualizar(Long id, Reserva dados) {
        Reserva reserva = buscarPorId(id);

        if (dados.getStatus() == null ||
            !dados.getStatus().equals("CANCELADA")) {
            throw new IllegalArgumentException(
                "A atualização permite apenas cancelar a reserva");
        }

        reserva.setStatus("CANCELADA");
        return reservas.save(reserva);
    }

    @Transactional
    public void excluir(Long id) {
        reservas.delete(buscarPorId(id));
    }
}
