package br.com.academico.professor;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface IProfessorService {
    
    public List<Professor> listar();
    public Professor recuperar(int matricula);
    public int criar(Professor professor);
    public Professor atualizar(int matricula, Professor professor);
    public int deletar(int matricula);
}
