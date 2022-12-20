package br.com.academico.sala;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface ISalaService {
    
    public List<Sala> listar();
    public Sala recuperar(int id);
    public int criar(Sala sala);
    public Sala atualizar(int id, Sala sala);
    public int deletar(int id);
}
