package br.com.academico.nota;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface INotaService {
    
    public List<Nota> listar();
    public Nota recuperar(Long id);
    public Long criar(Nota nota);
    public Nota atualizar(Long id, Nota nota);
    public Long deletar(Long id);
}
