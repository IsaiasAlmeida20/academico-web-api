package br.com.academico.turma;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface ITurmaService {
    
    public List<Turma> listar();
    public Turma recuperar(int id);
    public int criar(Turma turma);
    public Turma atualizar(int id, Turma turma);
    public int deletar(int id);
}
