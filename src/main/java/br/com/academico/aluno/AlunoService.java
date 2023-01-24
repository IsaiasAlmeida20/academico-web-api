package br.com.academico.aluno;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.jvnet.hk2.annotations.Service;

import br.com.academico.endereco.Endereco;

@Service
@Named("alunoservicedefaut")
public class AlunoService implements IAlunoService{
    
    public List<Aluno> listar() {

        List<Aluno> listAlunos = new ArrayList<Aluno>();

        Aluno a1 = new Aluno("Isaias", "Almeida", 24, "SE", 'M', "999.999.999-99", "Informatica", true);
        Aluno a2 = new Aluno("Joãozinho", "petorto", 20, "SE", 'M', "999.999.999-88", "Informatica", true);
        a1.setEndereco(new Endereco(49300000L, "Rua A", "Centro", "Tobias", "SE"));
        a2.setEndereco(new Endereco(49400000L, "Rua B", "Centro", "Lagarto", "BA"));
        listAlunos.add(a2);
        listAlunos.add(a1);
        Nota n1 = new Nota(8, 1);
        Nota n2 = new Nota(10, 1);
        Nota n3 = new Nota(9, 1);
        List<Nota> notas = new ArrayList<Nota>();
        notas.add(n1);
        notas.add(n2);
        notas.add(n3);
        a1.setNotas(notas);
        a2.setNotas(notas);
        a1.calcularMediaAritimetica();
        a1.calcularMediaPonderada();
        a2.calcularMediaAritimetica();
        a2.calcularMediaPonderada();

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

        Nota n1 = new Nota(8, 1);
        Nota n2 = new Nota(10, 1);
        Nota n3 = new Nota(9, 1);
        List<Nota> notas = new ArrayList<Nota>();
        notas.add(n1);
        notas.add(n2);
        notas.add(n3);

        return notas;
    }
}
