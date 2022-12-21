package br.com.academico.turma;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TurmaServiceTest {
    
    private TurmaService turmaService;

    @Before
    public void init() {
        turmaService = new TurmaService();
    }

    @Test
    public void teste_recuperar_lista_alunos() {
        List<Turma> listaTurmas = turmaService.listar();
        assertTrue("O retorno do método deve ser uma lista de turmas: ", listaTurmas instanceof List<?>);
    }

    @Test
    public void test_recuperar_aluno_por_id() {
        Turma aluno = turmaService.recuperar(10);
        assertTrue("O retorno do método recuperar deve ser um objeto Turma: ", aluno instanceof Turma);
    }

    @Test
    public void teste_criar_aluno() {
        Turma turma = new Turma("Informatica", "Primeiro");
        int idTurma = turmaService.criar(turma);
        assertTrue("O retorno do metodo criar deve ser o ID de uma Turma criada", idTurma == (int)idTurma);
    }

    @Test
    public void teste_atualizar_aluno_por_matricula(){
        Turma turma = new Turma();
        Turma turmaAtualizada = turmaService.atualizar(20, turma);
        assertTrue("O retorno do método atualizar deve ser um objeto Turma: ", turmaAtualizada instanceof Turma);
    }

    @Test
    public void teste_deletar_turma_por_matricula(){
        int idTurmaDeletada = turmaService.deletar(20);
        assertTrue("O retorno do método deletar deve ser um ID do Turma deletado: ", idTurmaDeletada == (int)idTurmaDeletada);
    }
}
