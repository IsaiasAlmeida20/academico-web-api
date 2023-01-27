package br.com.academico.disciplina;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.jvnet.hk2.annotations.Service;

@Service
@Named("disciplinaservicedefault")
public class DisciplinaService implements IDisciplinaService{

    private IDisciplinaRepository disciplinaRepository;

    @Inject
    public DisciplinaService(@Named("disciplinarepositoryJPA") IDisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public List<Disciplina> listar() {
        return disciplinaRepository.findAll();
    }

    public Disciplina recuperar(Long id) {
        return disciplinaRepository.getById(id)
            .orElseThrow(() -> new DisciplinaNaoExisteException());
    }

    public Long criar(Disciplina disciplina) {
        disciplinaRepository.save(disciplina);
        return disciplina.getId();
    }

    public Disciplina atualizar(Long id, Disciplina disciplina) {
        return disciplinaRepository.getById(id).map(d -> {
            d.setNomeDisciplina(disciplina.getNomeDisciplina());
            d.setCargaHoraria(disciplina.getCargaHoraria());
            disciplinaRepository.update(disciplina);
            return d;
        }).orElseThrow(() -> new DisciplinaNaoExisteException());
    }

    public Long deletar(Long id) {
        Optional<Disciplina> disciplina = disciplinaRepository.getById(id);
        if(disciplina.isPresent()) {
            disciplinaRepository.delete(disciplina.get().getId());
            return disciplina.get().getId();
        }else {
            throw new DisciplinaNaoExisteException();
        }
    }
    
}
