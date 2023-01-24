package br.com.academico.turma;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.jvnet.hk2.annotations.Service;

import br.com.academico.aluno.Aluno;
import br.com.academico.aluno.Nota;
import br.com.academico.disciplina.Disciplina;
import br.com.academico.endereco.Endereco;
import br.com.academico.professor.Professor;

@Service
@Named("turmaservicedefaut")
public class TurmaService implements ITurmaService{

    public List<Turma> listar() {

        List<Turma> listTurmas = new ArrayList<Turma>();

        Turma turma = new Turma("Informatica", "Primeiro");

        Endereco endereco = new Endereco(49300000L, "Rua a", "Macaé", "Tobias Barreto", "SE");

        Disciplina disciplina = new Disciplina("PROGRAMACAO", 100);

        Professor p1 = new Professor("Jose", "Silva", 34, "BA", 'M', "777.777.777-77", 5.000, 40);
        p1.setEndereco(endereco);
        
        List<Aluno> listAlunos = new ArrayList<Aluno>();
        Aluno a1 = new Aluno("Isaias", "Almeida", 24, "SE", 'M', "999.999.999-99", "Informatica", true);
        a1.setEndereco(endereco);
        Nota n1 = new Nota(8, 1);
        Nota n2 = new Nota(10, 1);
        Nota n3 = new Nota(9, 1);
        List<Nota> notas = new ArrayList<Nota>();
        notas.add(n1);
        notas.add(n2);
        notas.add(n3);
        a1.setNotas(notas);
        listAlunos.add(a1);

        turma.setAlunos(listAlunos);
        turma.setProfessor(p1);
        turma.setDisciplina(disciplina);

        listTurmas.add(turma);

        return listTurmas;

    }

    public Turma recuperar(int id) {
        Turma turma;
        if(id != 999) {
            turma = new Turma("Informatica", "Primeiro");
            turma.setId(id);
        }else {
            throw new TurmaNaoExisteException();
        }
        return turma;
    }

    public int criar(Turma turma) {
        if(turma.getNome() != "RandomName") {
            turma.setId(200);
        }else {
            throw new NomeTurmaInvalidoException();
        }
        return turma.getId();
    }

    public Turma atualizar(int id, Turma turma) {
        if(id != 999) {
            turma.setId(id);
            turma.setNome("Comercio");
        }else {
            throw new TurmaNaoExisteException();
        }
        return turma;
    }

    public int deletar(int id) {
        return id;
    }
    
}
