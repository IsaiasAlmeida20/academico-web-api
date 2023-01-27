package br.com.academico.nota;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.jvnet.hk2.annotations.Service;

import jakarta.persistence.EntityManager;

@Service
@Named("notarepositoryJPA")
public class NotaRepositoryJPA implements INotaRepository {
	
	private EntityManager em;
	
	@Inject
	public NotaRepositoryJPA(EntityManager entityManager) {
		this.em = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Nota> findAll() {
		return this.em.createQuery("from notas").getResultList();
	}

	@Override
	public Optional<Nota> getById(Long id) {
		em.getTransaction().begin();
        Nota nota = em.find(Nota.class, id);
        em.getTransaction().commit();
        return nota != null ? Optional.of(nota) : Optional.empty();
	}

	@Override
	public Nota save(Nota nota) {
		em.getTransaction().begin();
		em.persist(nota);
		em.getTransaction().commit();
		return nota;
	}

	@Override
	public Nota update(Nota nota) {
		em.getTransaction().begin();
		nota = em.merge(nota);
	    em.getTransaction().commit();
        return nota;
	}

	@Override
	public void delete(Long id) {
        em.getTransaction().begin();
		em.remove(em.find(Nota.class, id));
		em.getTransaction().commit();  
	}

}
