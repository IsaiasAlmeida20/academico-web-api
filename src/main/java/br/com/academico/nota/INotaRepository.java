package br.com.academico.nota;

import java.util.List;
import java.util.Optional;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface INotaRepository {

	public List<Nota> findAll();
	public Optional <Nota> getById(Long id);
	public Nota save(Nota nota);
	public Nota update(Nota nota);
	public void delete(Long id);
}
