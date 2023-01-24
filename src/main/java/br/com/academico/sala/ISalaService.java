package br.com.academico.sala;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface ISalaService {
    
    public List<Sala> listar();
    public Sala recuperar(Long id);
    public Long criar(Sala sala);
    public Sala atualizar(Long id, Sala sala);
    public Long deletar(Long id);
}
