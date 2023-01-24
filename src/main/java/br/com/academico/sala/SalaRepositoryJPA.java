package br.com.academico.sala;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.jvnet.hk2.annotations.Service;

import jakarta.persistence.EntityManager;

@Service
@Named("SalarepositoryJPA")
public class SalaRepositoryJPA implements ISalaRepository {
	
	private EntityManager em;
	
	@Inject
	public SalaRepositoryJPA(EntityManager entityManager) {
		this.em = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sala> findAll() {
		return this.em.createQuery("from salas").getResultList();
	}

	@Override
	public Optional<Sala> getById(Long id) {
		em.getTransaction().begin();
        Sala sala = em.find(Sala.class, id);
        em.getTransaction().commit();
        return sala != null ? Optional.of(sala) : Optional.empty();
	}

	@Override
	public Sala save(Sala sala) {
		em.getTransaction().begin();
		em.persist(sala);
		em.getTransaction().commit();
		return sala;
	}

	@Override
	public Sala update(Sala sala) {
		em.getTransaction().begin();
		sala = em.merge(sala);
	    em.getTransaction().commit();
        return sala;
	}

	@Override
	public void delete(Long id) {
        em.getTransaction().begin();
		em.remove(em.find(Sala.class, id));
		em.getTransaction().commit();  
	}

}
