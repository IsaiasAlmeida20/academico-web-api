package br.com.academico.turma;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.jvnet.hk2.annotations.Service;

@Service
@Named("turmaservicedefaut")
public class TurmaService implements ITurmaService{

    public List<Turma> listar() {

        List<Turma> listTurmas = new ArrayList<Turma>();

        return listTurmas;

    }

    public Turma recuperar(int id) {
        Turma turma;
        if(id != 999) {
            turma = new Turma("Informatica", "Primeiro");
            turma.setId(id);
        }else {
            throw new TurmaNaoExisteException();
        }
        return turma;
    }

    public int criar(Turma turma) {
        if(turma.getNome() != "RandomName") {
            turma.setId(200);
        }else {
            throw new NomeTurmaInvalidoException();
        }
        return turma.getId();
    }

    public Turma atualizar(int id, Turma turma) {
        if(id != 999) {
            turma.setId(id);
            turma.setNome("Comercio");
        }else {
            throw new TurmaNaoExisteException();
        }
        return turma;
    }

    public int deletar(int id) {
        return id;
    }
    
}
