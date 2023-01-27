package br.com.academico.nota;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class NotaRepositoryTest {

	private EntityManagerFactory emf;

	private EntityManager em;

	private INotaRepository notaRepositoryJPA;
	
	private Nota nota;

	@Before
	public void init() {
		emf = Persistence.createEntityManagerFactory("academico-pu");
		em = emf.createEntityManager();
		notaRepositoryJPA = new NotaRepositoryJPA(em);
		nota = new Nota(8.0, 2);
		nota.setMatricula(20239999L);
	}

	@Test
	public void test_findAll_notas() {

		List<Nota> listaNotasResposta = notaRepositoryJPA.findAll();

		assertThat(listaNotasResposta)
			.withFailMessage("O retorno do método listar deve ser uma lista de notas não nulla")
			.isNotNull();

		assertThat(listaNotasResposta)
			.withFailMessage("O retorno do método listar deve ser uma lista de notas")
			.isInstanceOf(List.class);

		assertThat(listaNotasResposta.size())
			.withFailMessage("O retorno do método listar deve ser uma lista de notas com a quantidade de notas no banco de dados")
			.isEqualTo(listaNotasResposta.size());
	}
	
    @Test
    public void teste_getById_notas(){

        notaRepositoryJPA.save(nota);
        
        Optional<Nota> notaRecuperada = notaRepositoryJPA.getById(nota.getId());

        assertThat(notaRecuperada.get())
            .withFailMessage("O retorno do método getById não pode ser nullo")
            .isNotNull();

        assertThat(notaRecuperada.get())
            .withFailMessage("O retorno do método getById deve ser um objeto Nota")
            .isInstanceOf(Nota.class);

        assertThat(notaRecuperada.get().getId())
            .withFailMessage("A nota recuperada deve ter o mesmo ID da nota recuperada")
            .isEqualTo(nota.getId());    
        
    }
	
	@Test
	public void test_save_nota() {
		
		Nota notaSalva = notaRepositoryJPA.save(nota);
		
		assertThat(notaSalva)
			.withFailMessage("O retorno do método não deve ser nullo")
			.isNotNull();
		
		assertThat(notaSalva)
	        .withFailMessage("O retorno do método save deve ser um objeto Nota")
	        .isInstanceOf(Nota.class);

	}
	
	  @Test
	    public void teste_update_nota(){

	        notaRepositoryJPA.save(nota);

	        nota.setValor(6.0);
	        nota.setPeso(1);
	        Nota notaAtualizada = notaRepositoryJPA.update(nota);

	        assertThat(notaAtualizada)
	            .withFailMessage("O retorno do método update não pode ser nullo")
	            .isNotNull();
	            
	        assertThat(notaAtualizada.getValor())
	            .withFailMessage("O número da sala deve ser igual ao informado")
	            .isEqualTo(6.0);
	            
	        assertThat(notaAtualizada.getPeso())
	            .withFailMessage("A capacidade deve ser igual ao informado")
	            .isEqualTo(1); 
	    
	    }
	  
	    @Test
	    public void teste_delete_nota(){

	        notaRepositoryJPA.save(nota);
	        
	        notaRepositoryJPA.delete(nota.getId());

	        Optional<Nota> notaDeletado = notaRepositoryJPA.getById(nota.getId());

	        assertThat(notaDeletado.isEmpty())
	            .withFailMessage("O retorno do método delete deve ser nullo")
	            .isEqualTo(true);     
	        
	    }

}
