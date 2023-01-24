package br.com.academico.sala;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class SalaRepositoryTest {

	private EntityManagerFactory emf;

	private EntityManager em;

	private ISalaRepository salaRepositoryJPA;
	
	private Sala sala;

	@Before
	public void init() {
		emf = Persistence.createEntityManagerFactory("academico-pu");
		em = emf.createEntityManager();
		salaRepositoryJPA = new SalaRepositoryJPA(em);
		sala = new Sala(1, 30, true,true, false);
	}

	@Test
	public void test_findAll_salas() {

		List<Sala> listaSalasResposta = salaRepositoryJPA.findAll();

		assertThat(listaSalasResposta)
			.withFailMessage("O retorno do método listar deve ser uma lista de salas não nulla")
			.isNotNull();

		assertThat(listaSalasResposta)
			.withFailMessage("O retorno do método listar deve ser uma lista de salas")
			.isInstanceOf(List.class);

		assertThat(listaSalasResposta.size())
			.withFailMessage("O retorno do método listar deve ser uma lista de salas com a quantidade de salas no banco de dados")
			.isEqualTo(listaSalasResposta.size());
	}
	
    @Test
    public void teste_getById_salas(){

        salaRepositoryJPA.save(sala);
        
        Optional<Sala> salaRecuperado = salaRepositoryJPA.getById(sala.getId());

        assertThat(salaRecuperado.get())
            .withFailMessage("O retorno do método getById não pode ser nullo")
            .isNotNull();

        assertThat(salaRecuperado.get())
            .withFailMessage("O retorno do método getById deve ser um objeto Sala")
            .isInstanceOf(Sala.class);

        assertThat(salaRecuperado.get().getId())
            .withFailMessage("O endereço recuperado deve ter o mesmo ID do endereço recuperado")
            .isEqualTo(sala.getId());    
        
    }
	
	@Test
	public void test_save_sala() {
		
		
		Sala salaSalvo = salaRepositoryJPA.save(sala);
		
		assertThat(salaSalvo)
			.withFailMessage("O retorno do método não deve ser nullo")
			.isNotNull();
		
		assertThat(salaSalvo)
	        .withFailMessage("O retorno do método save deve ser um objeto Sala")
	        .isInstanceOf(Sala.class);
		
		 assertThat(salaSalvo.getNumeroSala())
	        .withFailMessage("O número da sala deve ser igual a informada")
	        .isEqualTo(1);
        
	    assertThat(salaSalvo.getCapacidadeAlunos())
	        .withFailMessage("A capacidade deve ser igual ao informado")
	        .isEqualTo(30);
	        
	    assertThat(salaSalvo.isPossuiArcondicionado())
	        .withFailMessage("Deve retornar true")
	        .isEqualTo(true);
	        
	    assertThat(salaSalvo.isQuadroBranco())
	        .withFailMessage("Deve retornar true")
	        .isEqualTo(true);
	        
	    assertThat(salaSalvo.isLaboratorio())
	        .withFailMessage("Deve retornar false")
	        .isEqualTo(false); 
	}
	
	  @Test
	    public void teste_update_salas(){

	        salaRepositoryJPA.save(sala);
	        
	        sala.setNumeroSala(10);
	        sala.setCapacidadeAlunos(35);
	        sala.setLaboratorio(true);
	        Sala salaAtualizado = salaRepositoryJPA.update(sala);

	        assertThat(salaAtualizado)
	            .withFailMessage("O retorno do método update não pode ser nullo")
	            .isNotNull();
	            
	        assertThat(salaAtualizado.getNumeroSala())
	            .withFailMessage("O número da sala deve ser igual ao informado")
	            .isEqualTo(10);
	            
	        assertThat(salaAtualizado.getCapacidadeAlunos())
	            .withFailMessage("A capacidade deve ser igual ao informado")
	            .isEqualTo(35);
	            
	        assertThat(salaAtualizado.isLaboratorio())
	            .withFailMessage("Deve retornar true")
	            .isEqualTo(true);    
	    
	    }
	  
	    @Test
	    public void teste_delete_salas(){

	        salaRepositoryJPA.save(sala);
	        
	        salaRepositoryJPA.delete(sala.getId());

	        Optional<Sala> salaDeletado = salaRepositoryJPA.getById(sala.getId());

	        assertThat(salaDeletado.isEmpty())
	            .withFailMessage("O retorno do método delete deve ser nullo")
	            .isEqualTo(true);     
	        
	    }

}
