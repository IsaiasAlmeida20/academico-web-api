package br.com.academico.disciplina;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DisciplinaRepositoryTest {

	private EntityManagerFactory emf;

	private EntityManager em;

	private IDisciplinaRepository disciplinaRepositoryJPA;
	
	private Disciplina disciplina;

	@Before
	public void init() {
		emf = Persistence.createEntityManagerFactory("academico-pu");
		em = emf.createEntityManager();
		disciplinaRepositoryJPA = new DisciplinaRepositoryJPA(em);
		disciplina = new Disciplina("PROGRAMACAO", 120);
	}

	@Test
	public void test_findAll_disciplinas() {

		List<Disciplina> listaDisciplinasResposta = disciplinaRepositoryJPA.findAll();

		assertThat(listaDisciplinasResposta)
			.withFailMessage("O retorno do método listar deve ser uma lista de disciplinas não nulla")
			.isNotNull();

		assertThat(listaDisciplinasResposta)
			.withFailMessage("O retorno do método listar deve ser uma lista de disciplinas")
			.isInstanceOf(List.class);

		assertThat(listaDisciplinasResposta.size())
			.withFailMessage("O retorno do método listar deve ser uma lista de disciplinas com a quantidade de disciplinas no banco de dados")
			.isEqualTo(listaDisciplinasResposta.size());
	}
	
    @Test
    public void teste_getById_disciplin(){

        disciplinaRepositoryJPA.save(disciplina);
        
        Optional<Disciplina> dsiciplinaRecuperada = disciplinaRepositoryJPA.getById(disciplina.getId());

        assertThat(dsiciplinaRecuperada.get())
            .withFailMessage("O retorno do método getById não pode ser nullo")
            .isNotNull();

        assertThat(dsiciplinaRecuperada.get())
            .withFailMessage("O retorno do método getById deve ser um objeto Disciplina")
            .isInstanceOf(Disciplina.class);

        assertThat(dsiciplinaRecuperada.get().getId())
            .withFailMessage("A disciplina recuperada deve ter o mesmo ID da disciplina recuperado")
            .isEqualTo(disciplina.getId());    
        
    }
	
	@Test
	public void test_save_disciplina() {
		
		
		Disciplina disciplinaSalva = disciplinaRepositoryJPA.save(disciplina);
		
		assertThat(disciplinaSalva)
			.withFailMessage("O retorno do método não deve ser nullo")
			.isNotNull();
		
		assertThat(disciplinaSalva)
	        .withFailMessage("O retorno do método save deve ser um objeto Endereco")
	        .isInstanceOf(Disciplina.class);
        
	    assertThat(disciplinaSalva.getNomeDisciplina())
	        .withFailMessage("O nome da disciplina deve ser igual ao informado")
	        .isEqualTo("PROGRAMACAO");
	        
	    assertThat(disciplinaSalva.getCargaHoraria())
	        .withFailMessage("A Carga Horaria da disciplina deve ser igual a informada")
	        .isEqualTo(120);
	}
	
	  @Test
	    public void teste_update_disciplina(){

	        disciplinaRepositoryJPA.save(disciplina);
			disciplina.setNomeDisciplina("CS2");
			disciplina.setCargaHoraria(200);

	        Disciplina disciplinaAtualizada = disciplinaRepositoryJPA.update(disciplina);

	        assertThat(disciplinaAtualizada)
	            .withFailMessage("O retorno do método update não pode ser nullo")
	            .isNotNull();
	            
	        assertThat(disciplinaAtualizada.getNomeDisciplina())
	            .withFailMessage("O CEP atualizado do endereço deve ser igual ao informado")
	            .isEqualTo("CS2");
	            
	        assertThat(disciplinaAtualizada.getCargaHoraria())
	            .withFailMessage("A Cidade atualizada do endereço deve ser igual a informada")
	            .isEqualTo(200);   
	    
	    }
	  
	    @Test
	    public void teste_delete_enderecos(){

	        disciplinaRepositoryJPA.save(disciplina);
	        
	        disciplinaRepositoryJPA.delete(disciplina.getId());

	        Optional<Disciplina> disciplinaDeletada = disciplinaRepositoryJPA.getById(disciplina.getId());

	        assertThat(disciplinaDeletada.isEmpty())
	            .withFailMessage("O retorno do método delete deve ser nullo")
	            .isEqualTo(true);     
	        
	    }

}
