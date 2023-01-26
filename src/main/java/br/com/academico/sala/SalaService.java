package br.com.academico.sala;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.jvnet.hk2.annotations.Service;

@Service
@Named("salaservicedefault")
public class SalaService implements ISalaService{

    private ISalaRepository salaRepository;

    @Inject
    public SalaService(@Named("salarepositoryJPA") ISalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }
    
    public List<Sala> listar() {
        return salaRepository.findAll();
    }

    public Sala recuperar(Long id) {
        return salaRepository.getById(id)
            .orElseThrow(() -> new SalaNaoExisteException());
    }

    public Long criar(Sala sala) {
        salaRepository.save(sala);
        return sala.getId();
    }

    public Sala atualizar(Long id, Sala sala) {
        return salaRepository.getById(id).map(s -> {
            s.setCapacidadeAlunos(sala.getCapacidadeAlunos());
            s.setNumeroSala(sala.getNumeroSala());
            s.setLaboratorio(sala.isLaboratorio());
            s.setPossuiArcondicionado(sala.isPossuiArcondicionado());
            s.setQuadroBranco(sala.isQuadroBranco());
            salaRepository.update(s);
            return s;
        }).orElseThrow(() -> new SalaNaoExisteException());
    }

    public Long deletar(Long id) {
        Optional<Sala> sala = salaRepository.getById(id);
        if(sala.isPresent()) {
            salaRepository.delete(sala.get().getId());
            return sala.get().getId();
        }else {
            throw new SalaNaoExisteException();
        }
    }
}
