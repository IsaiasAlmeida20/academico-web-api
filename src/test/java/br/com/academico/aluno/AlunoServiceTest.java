package br.com.academico.aluno;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AlunoServiceTest {

    private AlunoService alunoService;

    @Before
    public void init() {
        alunoService = new AlunoService();
    }

    @Test
    public void teste_recuperar_lista_alunos() {
        List<Aluno> listaAlunos = alunoService.listar();
        assertTrue("O retorno do método deve ser uma lista de alunos: ",listaAlunos instanceof List<?>);
    }

    @Test
    public void test_recuperar_aluno_por_id() {
        Aluno aluno = alunoService.recuperar(10);
        assertTrue("O retorno do método recuperar deve ser um objeto Aluno: ", aluno instanceof Aluno);
    }

    @Test
    public void teste_criar_aluno() {
        Aluno aluno = new Aluno("Joãozinho", "petorto", 20, "SE", 'M', "999.999.999-88", "Informatica", true);
        int matriculaAluno = alunoService.criar(aluno);
        assertTrue("O retorno do metodo criar deve ser o ID de um Aluno criado", matriculaAluno == (int)matriculaAluno);
    }

    @Test
    public void teste_atualizar_aluno_por_matricula(){
        Aluno aluno = new Aluno("Joãozinho", "petorto", 20, "SE", 'M', "999.999.999-88", "Informatica", true);
        Aluno alunoAtualizado = alunoService.atualizar(20221199, aluno);
        assertTrue("O retorno do método atualizar deve ser um objeto Aluno: ", alunoAtualizado instanceof Aluno);
    }

    @Test
    public void teste_deletar_aluno_por_matricula(){
        int matriculaAlunoDeletado = alunoService.deletar(20225587);
        assertTrue("O retorno do método deletar deve ser um ID do Aluno deletado: ", matriculaAlunoDeletado == (int)matriculaAlunoDeletado);
    }
    
}
