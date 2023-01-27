package br.com.academico.disciplina;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface IDisciplinaService {
    
    public List<Disciplina> listar();
    public Disciplina recuperar(Long id);
    public Long criar(Disciplina disciplina);
    public Disciplina atualizar(Long id, Disciplina disciplina);
    public Long deletar(Long id);
}
