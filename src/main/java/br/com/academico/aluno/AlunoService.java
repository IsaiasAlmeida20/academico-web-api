package br.com.academico.aluno;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.jvnet.hk2.annotations.Service;

import br.com.academico.nota.Nota;

@Service
@Named("alunoservicedefaut")
public class AlunoService implements IAlunoService{
    
    public List<Aluno> listar() {

        List<Aluno> listAlunos = new ArrayList<Aluno>();

        return listAlunos;
        
    }

    public Aluno recuperar(int matricula) {
        Aluno aluno;
        if(matricula != 99999999) {
            aluno = new Aluno("Isaias", "Almeida", 24, "SE", 'M', "999.999.999-99", "Informatica", true);
            aluno.setMatricula(matricula);
        }else {
            throw new AlunoNaoExisteException();
        }
        return aluno;
    }

    public int criar(Aluno aluno) {
        if(aluno.getMatricula() != 99999999) {
            aluno.setMatricula(20223322);
        }else {
            throw new MatriculaAlunoInvalidoException();
        }
        return aluno.getMatricula();
    }

    public Aluno atualizar(int matricula, Aluno aluno) {
        if(matricula != 99999999) {
            aluno.setMatricula(matricula);
            aluno.setNome("Manoel");
        }else {
            throw new AlunoNaoExisteException();
        }
        return aluno;
    }

    public int deletar(int matricula) {
        return matricula;
    }

    public List<Nota> listarNotas(int matricula) {

        
        List<Nota> notas = new ArrayList<Nota>();
       

        return notas;
    }
}
