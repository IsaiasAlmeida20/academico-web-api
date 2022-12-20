package br.com.academico.disciplina;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface IDisciplinaService {
    
    public List<Disciplina> listar();
    public Disciplina recuperar(int id);
    public int criar(Disciplina disciplina);
    public Disciplina atualizar(int id, Disciplina disciplina);
    public int deletar(int id);
}
