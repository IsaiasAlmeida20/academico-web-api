package br.com.academico.disciplina;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.jvnet.hk2.annotations.Service;

@Service
@Named("disciplinaservicedefaut")
public class DisciplinaService implements IDisciplinaService{

    public List<Disciplina> listar() {
        List<Disciplina> listDisciplinas = new ArrayList<Disciplina>();
        listDisciplinas.add(new Disciplina("PROGRAMACAO", 100));
        listDisciplinas.add(new Disciplina("ENGENHARIA", 110));
        return listDisciplinas;
    }

    public Disciplina recuperar(int id) {
        Disciplina disciplina;
        if(id != 999) {
            disciplina = new Disciplina("ENGENHARIA", 110);
            disciplina.setId(id);
        }else {
            throw new DisciplinaNaoExisteException();
        }
        return disciplina;
    }

    public int criar(Disciplina disciplina) {
        if(disciplina.getNomeDisciplina() != "PROGRAMAÇÃO") {
            disciplina.setId(200);
        }else {
            throw new NomeDisciplinaInvalidoException();
        }
        return disciplina.getId();
    }

    public Disciplina atualizar(int id, Disciplina disciplina) {
        if(id != 999) {
            disciplina.setId(id);
            disciplina.setNomeDisciplina("CONTRUÇÃO DE SITES");
        }else {
            throw new DisciplinaNaoExisteException();
        }
        return disciplina;
    }

    public int deletar(int id) {
        return id;
    }
    
}
