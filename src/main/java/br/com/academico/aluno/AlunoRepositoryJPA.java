package br.com.academico.aluno;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.jvnet.hk2.annotations.Service;

import jakarta.persistence.EntityManager;

@Service
@Named("alunorepositoryJPA")
public class AlunoRepositoryJPA implements IAlunoRepository {
	
	private EntityManager em;
	
	@Inject
	public AlunoRepositoryJPA(EntityManager entityManager) {
		this.em = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> findAll() {
		return this.em.createQuery("from alunos").getResultList();
	}

	@Override
	public Optional<Aluno> getById(Long matricula) {
		em.getTransaction().begin();
        Aluno aluno = em.find(Aluno.class, matricula);
        em.getTransaction().commit();
        return aluno != null ? Optional.of(aluno) : Optional.empty();
	}

	@Override
	public Aluno save(Aluno aluno) {
		em.getTransaction().begin();
		em.persist(aluno);
		em.getTransaction().commit();
		return  aluno;
	}

	@Override
	public Aluno update(Aluno aluno) {
		em.getTransaction().begin();
		aluno = em.merge(aluno);
	    em.getTransaction().commit();
        return aluno;
	}

	@Override
	public void delete(Long matricula) {
        em.getTransaction().begin();
		em.remove(em.find(Aluno.class, matricula));
		em.getTransaction().commit();  
	}

}
