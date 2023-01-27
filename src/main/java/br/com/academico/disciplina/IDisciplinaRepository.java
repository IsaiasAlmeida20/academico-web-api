package br.com.academico.disciplina;

import java.util.List;
import java.util.Optional;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface IDisciplinaRepository {

	public List<Disciplina> findAll();
	public Optional <Disciplina> getById(Long id);
	public Disciplina save(Disciplina disciplina);
	public Disciplina update(Disciplina disciplina);
	public void delete(Long id);
}
