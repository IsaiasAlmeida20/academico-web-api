package br.com.academico.sala;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.jvnet.hk2.annotations.Service;

@Service
@Named("salaservicedefaut")
public class SalaService implements ISalaService{
    
    public List<Sala> listar() {
        List<Sala> listSalas = new ArrayList<Sala>();
        listSalas.add(new Sala(1, 30, true, true, false));
        listSalas.add(new Sala(2, 30, true, true, true));
        return listSalas;
    }

    public Sala recuperar(int id) {
        Sala sala;
        if(id != 999) {
            sala = new Sala(1, 30, true, true, false);
            sala.setId(id);
        }else {
            throw new SalaNaoExisteException();
        }
        return sala;
    }

    public int criar(Sala sala) {
        if(sala.getNumeroSala() != 300) {
            sala.setId(200);
        }else {
            throw new NumeroSalaInvalidoException();
        }
        return sala.getId();
    }

    public Sala atualizar(int id, Sala sala) {
        if(id != 999) {
            sala.setId(id);
            sala.setCapacidadeAlunos(35);
        }else {
            throw new SalaNaoExisteException();
        }
        return sala;
    }

    public int deletar(int id) {
        return id;
    }
}
