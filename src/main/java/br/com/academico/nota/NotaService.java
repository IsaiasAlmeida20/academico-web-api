package br.com.academico.nota;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.jvnet.hk2.annotations.Service;

@Service
@Named("notaservicedefault")
public class NotaService implements INotaService{

    private INotaRepository notaRepository;

    @Inject
    public NotaService(@Named("notarepositoryJPA") INotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }
    
    public List<Nota> listar() {
        return notaRepository.findAll();
    }

    public Nota recuperar(Long id) {
        return notaRepository.getById(id)
            .orElseThrow(() -> new NotaNaoExisteException());
    }

    public Long criar(Nota nota) {
        notaRepository.save(nota);
        return nota.getId();
    }

    public Nota atualizar(Long id, Nota nota) {
        return notaRepository.getById(id).map(n -> {
            n.setMatricula(nota.getMatricula());
            n.setValor(nota.getValor());
            n.setPeso(nota.getPeso());
            notaRepository.update(n);
            return n;
        }).orElseThrow(() -> new NotaNaoExisteException());
    }

    public Long deletar(Long id) {
        Optional<Nota> nota = notaRepository.getById(id);
        if(nota.isPresent()) {
            notaRepository.delete(nota.get().getId());
            return nota.get().getId();
        }else {
            throw new NotaNaoExisteException();
        }
    }
}
