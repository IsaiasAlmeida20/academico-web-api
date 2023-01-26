package br.com.academico.sala;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;


public class SalaServiceTest {

    private SalaService salaService;

    private ISalaRepository salaRepositoryMocked;

    private Sala sala;

    private Long idSala;

    @Before
    public void init() {
        salaRepositoryMocked = mock(ISalaRepository.class);
        salaService = new SalaService(salaRepositoryMocked);
        sala = new Sala(1, 30, true,true, false);
        idSala = 1L;
    }

    @Test
    public void teste_recuperar_lista_salas() {
        List<Sala> listSalaEsperada;
        listSalaEsperada = new ArrayList<Sala>();
        listSalaEsperada.add(sala);
        listSalaEsperada.add(sala);

        given(salaRepositoryMocked.findAll()).willReturn(listSalaEsperada);

        List<Sala> listSalaResposta = salaService.listar();

        assertThat(listSalaResposta)
            .withFailMessage("O retorno do método listar deve ser uma lista de salas")
            .isInstanceOf(List.class);

        assertThat(listSalaResposta)
            .withFailMessage("O retorno do método listar deve ser uma lista de endereços não nulla")
            .isNotNull();

        assertThat(listSalaResposta.size())
            .withFailMessage("O retorno do método listar deve ser uma lista de sala com duas salas")
            .isEqualTo(2);
    }

    @Test
    public void test_recuperar_sala_por_id() {
        sala.setId(idSala);

          given(salaRepositoryMocked.getById(idSala)).willReturn(Optional.of(sala));

          Sala salaResposta = salaService.recuperar(idSala);

          assertThat(salaResposta)
              .withFailMessage("O retorno do método recuperar não pode ser nullo")
              .isNotNull();     

          assertThat(salaResposta)
              .withFailMessage("O retorno do método recuperar deve ser um objeto Sala")
              .isInstanceOf(Sala.class);
    }

    @Test
    public void teste_criar_sala() {

        sala.setId(idSala);

        given(salaRepositoryMocked.save(sala)).willReturn(sala);

        Long idSalaSalva = salaService.criar(sala);

        assertThat(idSalaSalva)
            .withFailMessage("O retorno do método criar não pode ser um ID nullo")
            .isNotNull();   

        assertThat(idSalaSalva)
            .withFailMessage("O retorno do método criar deve ser um ID de uma Sala")
            .isInstanceOf(Long.class);
    }

    @Test
    public void teste_atualizar_sala_por_id(){
        sala.setId(idSala);

        given(salaRepositoryMocked.getById(sala.getId()))
            .willReturn(Optional.of(sala));

        given(salaRepositoryMocked.update(sala))
            .willAnswer(invocation -> invocation.getArgument(0));

        Sala salaAtualizada = salaService.atualizar(sala.getId(), sala);

        assertThat(salaAtualizada)
            .withFailMessage("O retorno do método atualizar não pode ser uma Sala nulla")
            .isNotNull();   

        assertThat(salaAtualizada)
            .withFailMessage("O retorno do método atualizar deve ser um objeto Sala")
            .isInstanceOf(Sala.class);
    }

    @Test
    public void teste_deletar_sala_por_id(){
    	sala.setId(idSala);

        given(salaRepositoryMocked.getById(idSala))
            .willReturn(Optional.of(sala));

        willDoNothing().given(salaRepositoryMocked).delete(idSala);

        Long idSalaDeletada = salaService.deletar(idSala);

        assertThat(idSalaDeletada)
            .withFailMessage("O retorno do método deletar não pode ser um ID nullo")
            .isNotNull();   

        assertThat(idSalaDeletada)
            .withFailMessage("O retorno do método deletar deve ser um ID de uma Sala deletada")
            .isInstanceOf(Long.class);
    }
    
}
