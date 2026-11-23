package br.edu.unifio.ecommerce.servicos;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.edu.unifio.ecommerce.entidades.*;
import br.edu.unifio.ecommerce.repositorios.*;
import br.edu.unifio.ecommerce.excecoes.ConflitoException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservaServicoTests {

    @Mock
    private ReservaRepositorio reservas;

    @Mock
    private UsuarioRepositorio usuarios;

    @Mock
    private ViagemRepositorio viagens;

    @InjectMocks
    private ReservaServico servico;

    private Usuario criarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Aluno Teste");
        usuario.setEmail("teste@example.com");
        usuario.setSenha("Teste@123");
        usuario.setPerfil("ALUNO");
        return usuario;
    }

    private Viagem criarViagem() {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(1L);
        veiculo.setCapacidade(1);

        Viagem viagem = new Viagem();
        viagem.setId(1L);
        viagem.setVeiculo(veiculo);
        viagem.setStatus("AGENDADA");

        return viagem;
    }

    @Test
    void deveCadastrarReservaComSucesso() {
        Usuario usuario = criarUsuario();
        Viagem viagem = criarViagem();

        when(usuarios.findById(1L))
            .thenReturn(Optional.of(usuario));

        when(viagens.findById(1L))
            .thenReturn(Optional.of(viagem));

        when(reservas.existsByUsuarioIdAndViagemIdAndStatus(
            1L, 1L, "CONFIRMADA")).thenReturn(false);

        when(reservas.countByViagemIdAndStatus(
            1L, "CONFIRMADA")).thenReturn(0L);

        when(reservas.save(any(Reserva.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        Reserva dados = new Reserva();
        dados.setUsuario(usuario);
        dados.setViagem(viagem);

        Reserva resultado = servico.cadastrar(dados);

        assertEquals("CONFIRMADA", resultado.getStatus());
        assertEquals(usuario, resultado.getUsuario());
        assertEquals(viagem, resultado.getViagem());

        verify(reservas).save(dados);
    }

    @Test
    void naoDevePermitirReservaDuplicada() {
        Usuario usuario = criarUsuario();
        Viagem viagem = criarViagem();

        when(usuarios.findById(1L))
            .thenReturn(Optional.of(usuario));

        when(viagens.findById(1L))
            .thenReturn(Optional.of(viagem));

        when(reservas.existsByUsuarioIdAndViagemIdAndStatus(
            1L, 1L, "CONFIRMADA")).thenReturn(true);

        Reserva dados = new Reserva();
        dados.setUsuario(usuario);
        dados.setViagem(viagem);

        assertThrows(ConflitoException.class,
            () -> servico.cadastrar(dados));

        verify(reservas, never()).save(any());
    }

    @Test
    void naoDevePermitirReservaComVeiculoLotado() {
        Usuario usuario = criarUsuario();
        Viagem viagem = criarViagem();

        when(usuarios.findById(1L))
            .thenReturn(Optional.of(usuario));

        when(viagens.findById(1L))
            .thenReturn(Optional.of(viagem));

        when(reservas.existsByUsuarioIdAndViagemIdAndStatus(
            1L, 1L, "CONFIRMADA")).thenReturn(false);

        when(reservas.countByViagemIdAndStatus(
            1L, "CONFIRMADA")).thenReturn(1L);

        Reserva dados = new Reserva();
        dados.setUsuario(usuario);
        dados.setViagem(viagem);

        assertThrows(ConflitoException.class,
            () -> servico.cadastrar(dados));

        verify(reservas, never()).save(any());
    }

    @Test
    void deveCancelarReservaComSucesso() {
        Reserva reserva = new Reserva();
        reserva.setId(1L);
        reserva.setStatus("CONFIRMADA");

        when(reservas.findById(1L))
            .thenReturn(Optional.of(reserva));

        when(reservas.save(any(Reserva.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        Reserva dados = new Reserva();
        dados.setStatus("CANCELADA");

        Reserva resultado = servico.atualizar(1L, dados);

        assertEquals("CANCELADA", resultado.getStatus());

        verify(reservas).save(reserva);
    }
}
