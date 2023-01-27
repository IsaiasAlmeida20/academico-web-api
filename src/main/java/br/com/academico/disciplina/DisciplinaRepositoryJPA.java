package br.com.academico.disciplina;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.jvnet.hk2.annotations.Service;

import jakarta.persistence.EntityManager;

@Service
@Named("disciplinarepositoryJPA")
public class DisciplinaRepositoryJPA implements IDisciplinaRepository {
	
	private EntityManager em;
	
	@Inject
	public DisciplinaRepositoryJPA(EntityManager entityManager) {
		this.em = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Disciplina> findAll() {
		return this.em.createQuery("from disciplinas").getResultList();
	}

	@Override
	public Optional<Disciplina> getById(Long id) {
		em.getTransaction().begin();
        Disciplina disciplina = em.find(Disciplina.class, id);
        em.getTransaction().commit();
        return disciplina != null ? Optional.of(disciplina) : Optional.empty();
	}

	@Override
	public Disciplina save(Disciplina disciplina) {
		em.getTransaction().begin();
		em.persist(disciplina);
		em.getTransaction().commit();
		return disciplina;
	}

	@Override
	public Disciplina update(Disciplina disciplina) {
		em.getTransaction().begin();
		disciplina = em.merge(disciplina);
	    em.getTransaction().commit();
        return disciplina;
	}

	@Override
	public void delete(Long id) {
        em.getTransaction().begin();
		em.remove(em.find(Disciplina.class, id));
		em.getTransaction().commit();  
	}

}
