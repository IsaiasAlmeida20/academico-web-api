package br.com.academico.professor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.jvnet.hk2.annotations.Service;

import br.com.academico.endereco.Endereco;

@Service
@Named("professorservicedefaut")
public class ProfessorService implements IProfessorService {

    public List<Professor> listar() {
        
        List<Professor> listProfessores = new ArrayList<Professor>();
        Professor p1 = new Professor("Jose", "Silva", 34, "BA", 'M', "777.777.777-77", 5.000, 40);
        Professor p2 = new Professor("Joao", "Barros", 44, "SE", 'M', "777.555.777-77", 5.700, 42);
        p1.setEndereco(new Endereco(49300000, "Rua A", "Centro", "Tobias", "SE"));
        p2.setEndereco(new Endereco(49400000, "Rua B", "Centro", "Lagarto", "BA"));
        listProfessores.add(p2);
        listProfessores.add(p1);

        return listProfessores;
    }

    public Professor recuperar(int matricula) {
        Professor professor;
        if(matricula != 99999999) {
            professor = new Professor("Jose", "Silva", 34, "BA", 'M', "777.777.777-77", 5.000, 40);
            professor.setMatricula(matricula);
        }else {
            throw new ProfessorNaoExisteException();
        }
        return professor;
    }

    public int criar(Professor professor) {
        if(professor.getMatricula() != 99999999) {
            professor.setMatricula(20223322);
        }else {
            throw new MatriculaProfessorInvalidoException();
        }
        return professor.getMatricula();
    }

    public Professor atualizar(int matricula, Professor professor) {
        if(matricula != 99999999) {
            professor.setMatricula(matricula);
            professor.setNome("José");
        }else {
            throw new ProfessorNaoExisteException();
        }
        return professor;
    }

    public int deletar(int matricula) {
        return matricula;
    }
    
}
