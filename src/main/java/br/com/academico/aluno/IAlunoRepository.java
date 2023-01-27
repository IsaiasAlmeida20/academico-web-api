package br.com.academico.aluno;

import java.util.List;
import java.util.Optional;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface IAlunoRepository {
    public List<Aluno> findAll();
	public Optional <Aluno> getById(Long id);
	public Aluno save(Aluno aluno);
	public Aluno update(Aluno aluno);
	public void delete(Long id);
}
