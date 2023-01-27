package br.com.academico.nota;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;


public class NotaServiceTest {

    private NotaService notaService;

    private INotaRepository notaRepositoryMocked;

    private Nota nota;

    private Long idNota;

    @Before
    public void init() {
        notaRepositoryMocked = mock(INotaRepository.class);
        notaService = new NotaService(notaRepositoryMocked);
        nota = new Nota(8.0, 2);
		nota.setMatricula(20239999L);
        idNota = 1L;
    }

    @Test
    public void teste_recuperar_lista_notas() {
        List<Nota> listNotaEsperada;
        listNotaEsperada = new ArrayList<Nota>();
        listNotaEsperada.add(nota);
        listNotaEsperada.add(nota);

        given(notaRepositoryMocked.findAll()).willReturn(listNotaEsperada);

        List<Nota> listNotaResposta = notaService.listar();

        assertThat(listNotaResposta)
            .withFailMessage("O retorno do método listar deve ser uma lista de notas")
            .isInstanceOf(List.class);

        assertThat(listNotaResposta)
            .withFailMessage("O retorno do método listar deve ser uma lista de notas não nulla")
            .isNotNull();

        assertThat(listNotaResposta.size())
            .withFailMessage("O retorno do método listar deve ser uma lista de notas com duas notas")
            .isEqualTo(2);
    }

    @Test
    public void test_recuperar_nota_por_id() {
        nota.setId(idNota);

          given(notaRepositoryMocked.getById(idNota)).willReturn(Optional.of(nota));

          Nota notaResposta = notaService.recuperar(idNota);

          assertThat(notaResposta)
              .withFailMessage("O retorno do método recuperar não pode ser nullo")
              .isNotNull();     

          assertThat(notaResposta)
              .withFailMessage("O retorno do método recuperar deve ser um objeto Sala")
              .isInstanceOf(Nota.class);
    }

    @Test
    public void teste_criar_nota() {

        nota.setId(idNota);

        given(notaRepositoryMocked.save(nota)).willReturn(nota);

        Long idNotaSalva = notaService.criar(nota);

        assertThat(idNotaSalva)
            .withFailMessage("O retorno do método criar não pode ser um ID nullo")
            .isNotNull();   

        assertThat(idNotaSalva)
            .withFailMessage("O retorno do método criar deve ser um ID de uma Nota")
            .isInstanceOf(Long.class);
    }

    @Test
    public void teste_atualizar_nota_por_id(){
        nota.setId(idNota);

        given(notaRepositoryMocked.getById(nota.getId()))
            .willReturn(Optional.of(nota));

        given(notaRepositoryMocked.update(nota))
            .willAnswer(invocation -> invocation.getArgument(0));

        Nota notaAtualizada = notaService.atualizar(nota.getId(), nota);

        assertThat(notaAtualizada)
            .withFailMessage("O retorno do método atualizar não pode ser uma Nota nulla")
            .isNotNull();   

        assertThat(notaAtualizada)
            .withFailMessage("O retorno do método atualizar deve ser um objeto Nota")
            .isInstanceOf(Nota.class);
    }

    @Test
    public void teste_deletar_nota_por_id(){
    	nota.setId(idNota);

        given(notaRepositoryMocked.getById(idNota))
            .willReturn(Optional.of(nota));

        willDoNothing().given(notaRepositoryMocked).delete(idNota);

        Long idNotaDeletada = notaService.deletar(idNota);

        assertThat(idNotaDeletada)
            .withFailMessage("O retorno do método deletar não pode ser um ID nullo")
            .isNotNull();   

        assertThat(idNotaDeletada)
            .withFailMessage("O retorno do método deletar deve ser um ID de uma Nota deletada")
            .isInstanceOf(Long.class);
    }
    
}
