package br.com.academico.disciplina;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class DisciplinaServiceTest{
    
    private DisciplinaService disciplinaService;

    private IDisciplinaRepository disciplinaRepositoryMocked;

    private Disciplina disciplina;

    private Long idDisciplina;

    @Before
    public void init() {
        disciplinaRepositoryMocked = mock(IDisciplinaRepository.class);
        disciplinaService = new DisciplinaService(disciplinaRepositoryMocked);
        disciplina = new Disciplina("PROGRAMAÇÃO", 120);
        idDisciplina = 1L;
    }

    @Test
    public void teste_recuperar_lista_disciplinas() {
        List<Disciplina> listaDisciplinaEsperada = new ArrayList<Disciplina>();
        listaDisciplinaEsperada.add(disciplina);
        listaDisciplinaEsperada.add(disciplina);

        given(disciplinaRepositoryMocked.findAll()).willReturn(listaDisciplinaEsperada);

        List<Disciplina> listDisciplinaResposta = disciplinaService.listar();

        assertThat(listDisciplinaResposta)
            .withFailMessage("O retorno do método listar deve ser uma lista de disciplinas")
            .isInstanceOf(List.class);

        assertThat(listDisciplinaResposta)
            .withFailMessage("O retorno do método listar deve ser uma lista de disciplinas não nulla")
            .isNotNull();

        assertThat(listDisciplinaResposta.size())
            .withFailMessage("O retorno do método listar deve ser uma lista de endereços com duas dsiciplinas")
            .isEqualTo(2);
    }

    @Test
    public void test_recuperar_disciplina_por_id() {
        disciplina.setId(idDisciplina);

        given(disciplinaRepositoryMocked.getById(idDisciplina)).willReturn(Optional.of(disciplina));

        Disciplina disciplinaResposta = disciplinaService.recuperar(idDisciplina);

        assertThat(disciplinaResposta)
              .withFailMessage("O retorno do método recuperar não pode ser nullo")
              .isNotNull();     

          assertThat(disciplinaResposta)
              .withFailMessage("O retorno do método recuperar deve ser um objeto Disciplina")
              .isInstanceOf(Disciplina.class);
    }

    @Test
    public void teste_criar_disciplina() {
        disciplina.setId(idDisciplina);

        given(disciplinaRepositoryMocked.save(disciplina)).willReturn(disciplina);

        Long idDisciplinaSalva = disciplinaService.criar(disciplina);

        assertThat(idDisciplinaSalva)
            .withFailMessage("O retorno do método criar não pode ser um ID nullo")
            .isNotNull();   

        assertThat(idDisciplinaSalva)
            .withFailMessage("O retorno do método criar deve ser um ID de uma Dsiciplina")
            .isInstanceOf(Long.class);
    }

    @Test
    public void teste_atualizar_disciplina_por_id(){
        disciplina.setId(idDisciplina);

        given(disciplinaRepositoryMocked.getById(disciplina.getId()))
            .willReturn(Optional.of(disciplina));

        given(disciplinaRepositoryMocked.update(disciplina))
            .willAnswer(invocation -> invocation.getArgument(0));

        Disciplina disciplinaAtualizada = disciplinaService.atualizar(disciplina.getId(), disciplina);

        assertThat(disciplinaAtualizada)
        .withFailMessage("O retorno do método atualizar não pode ser uma Disciplina nulla")
        .isNotNull();   

    assertThat(disciplinaAtualizada)
        .withFailMessage("O retorno do método atualizar deve ser um objeto Dsiciplina")
        .isInstanceOf(Disciplina.class);
    }

    @Test
    public void teste_deletar_disciplina_por_id(){
        disciplina.setId(idDisciplina);

        given(disciplinaRepositoryMocked.getById(idDisciplina))
            .willReturn(Optional.of(disciplina));

        willDoNothing().given(disciplinaRepositoryMocked).delete(idDisciplina);

        Long idDisciplinaDeletada = disciplinaService.deletar(idDisciplina);

        assertThat(idDisciplinaDeletada)
            .withFailMessage("O retorno do método deletar não pode ser um ID nullo")
            .isNotNull();   

        assertThat(idDisciplinaDeletada)
            .withFailMessage("O retorno do método deletar deve ser um ID de uma disciplina deletada")
            .isInstanceOf(Long.class);
    }
}
