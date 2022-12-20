package br.com.academico.aluno;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface IAlunoService {
    
    public List<Aluno> listar();
    public Aluno recuperar(int matricula);
    public int criar(Aluno aluno);
    public Aluno atualizar(int matricula, Aluno aluno);
    public int deletar(int matricula);
    public List<Nota> listarNotas(int matricula);

}
