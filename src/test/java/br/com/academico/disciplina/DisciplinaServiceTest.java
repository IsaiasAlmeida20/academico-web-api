package br.com.academico.disciplina;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DisciplinaServiceTest{
    
    private DisciplinaService disciplinaService;

    @Before
    public void init() {
        disciplinaService = new DisciplinaService();
    }

    @Test
    public void teste_recuperar_lista_disciplinas() {
        List<Disciplina> listaDisciplinas = disciplinaService.listar();
        assertTrue("O retorno do método deve ser uma lista de disciplinas: ", listaDisciplinas instanceof List<?>);
    }

    @Test
    public void test_recuperar_disciplina_por_id() {
        Disciplina disciplina = disciplinaService.recuperar(10);
        assertTrue("O retorno do método recuperar deve ser um objeto Disciplina: ", disciplina instanceof Disciplina);
    }

    @Test
    public void teste_criar_disciplina() {
        Disciplina disciplina = new Disciplina("0000-PROGRAMAÇÃO", 100);
        int idDisciplina = disciplinaService.criar(disciplina);
        assertTrue("O retorno do metodo criar deve ser o ID de uma Disciplina", idDisciplina == (int)idDisciplina);
    }

    @Test
    public void teste_atualizar_disciplina_por_id(){
        Disciplina disciplina = new Disciplina("PROGRAMAÇÃO", 100);
        Disciplina disciplinaAtualizada = disciplinaService.atualizar(12, disciplina);
        assertTrue("O retorno do método atualizar deve ser um objeto Disciplina: ", disciplinaAtualizada instanceof Disciplina);
    }

    @Test
    public void teste_deletar_disciplina_por_id(){
        int idDisciplinaDeletada = disciplinaService.deletar(587);
        assertTrue("O retorno do método deletar deve ser um ID da Disciplina deletada: ", idDisciplinaDeletada == (int)idDisciplinaDeletada);
    }
}
