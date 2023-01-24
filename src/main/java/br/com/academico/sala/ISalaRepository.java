package br.com.academico.sala;

import java.util.List;
import java.util.Optional;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface ISalaRepository {

	public List<Sala> findAll();
	public Optional <Sala> getById(Long id);
	public Sala save(Sala sala);
	public Sala update(Sala sala);
	public void delete(Long id);
}
