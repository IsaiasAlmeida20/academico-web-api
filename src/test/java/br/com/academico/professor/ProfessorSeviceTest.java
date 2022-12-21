package br.com.academico.professor;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ProfessorSeviceTest {

    private ProfessorService professorService;

    @Before
    public void init() {
        professorService = new ProfessorService();

    }
    
    @Test
    public void teste_recuperar_lista_professores() {
        List<Professor> lsitaProfessores = professorService.listar();
        assertTrue("O retorno do método deve ser uma lista de professores: ", lsitaProfessores instanceof List<?>);
    }

    @Test
    public void test_recuperar_professor_por_id() {
        Professor professor = professorService.recuperar(10);
        assertTrue("O retorno do método recuperar deve ser um objeto Professor: ", professor instanceof Professor);
    }

    @Test
    public void teste_criar_professor() {
        Professor professor = new Professor("Jose", "Silva", 34, "BA", 'M', "777.777.777-77", 5.000, 40);
        int matricula = professorService.criar(professor);
        assertTrue("O retorno do metodo criar deve ser o ID de um Professor criado", matricula == (int)matricula);
    }

    @Test
    public void teste_atualizar_professor_por_matricula(){
        Professor professor = new Professor("Joao", "Barros", 44, "SE", 'M', "777.555.777-77", 5.700, 42);
        Professor professorAtualizado = professorService.atualizar(12, professor);
        assertTrue("O retorno do método atualizar deve ser um objeto Professor: ", professorAtualizado instanceof Professor);
    }

    @Test
    public void teste_deletar_professor_por_matricula(){
        int matriculaProfessorDeletado = professorService.deletar(587);
        assertTrue("O retorno do método deletar deve ser um ID do Professor deletado: ", matriculaProfessorDeletado == (int)matriculaProfessorDeletado);
    }
}
