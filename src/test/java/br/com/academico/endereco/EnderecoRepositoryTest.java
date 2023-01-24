package br.com.academico.endereco;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EnderecoRepositoryTest {

	private EntityManagerFactory emf;

	private EntityManager em;

	private IEnderecoRepository enderecoRepositoryJPA;
	
	private Endereco endereco;

	@Before
	public void init() {
		emf = Persistence.createEntityManagerFactory("academico-pu");
		em = emf.createEntityManager();
		enderecoRepositoryJPA = new EnderecoRepositoryJPA(em);
		endereco = new Endereco(49300000L, "Rua a", "Macaé", "Tobias Barreto", "SE");
	}

	@Test
	public void test_findAll_enderecos() {

		List<Endereco> listaEnderecosResposta = enderecoRepositoryJPA.findAll();

		assertThat(listaEnderecosResposta)
			.withFailMessage("O retorno do método listar deve ser uma lista de endereços não nulla")
			.isNotNull();

		assertThat(listaEnderecosResposta)
			.withFailMessage("O retorno do método listar deve ser uma lista de endereços")
			.isInstanceOf(List.class);

		assertThat(listaEnderecosResposta.size())
			.withFailMessage("O retorno do método listar deve ser uma lista de endereços com a quantidade de endereços no banco de dados")
			.isEqualTo(listaEnderecosResposta.size());
	}
	
    @Test
    public void teste_getById_enderecos(){

        enderecoRepositoryJPA.save(endereco);
        
        Optional<Endereco> enderecoRecuperado = enderecoRepositoryJPA.getById(endereco.getId());

        assertThat(enderecoRecuperado.get())
            .withFailMessage("O retorno do método getById não pode ser nullo")
            .isNotNull();

        assertThat(enderecoRecuperado.get())
            .withFailMessage("O retorno do método getById deve ser um objeto Endereco")
            .isInstanceOf(Endereco.class);

        assertThat(enderecoRecuperado.get().getId())
            .withFailMessage("O endereço recuperado deve ter o mesmo ID do endereço recuperado")
            .isEqualTo(endereco.getId());    
        
    }
	
	@Test
	public void test_save_endereco() {
		
		
		Endereco enderecoSalvo = enderecoRepositoryJPA.save(endereco);
		
		assertThat(enderecoSalvo)
			.withFailMessage("O retorno do método não deve ser nullo")
			.isNotNull();
		
		assertThat(enderecoSalvo)
	        .withFailMessage("O retorno do método save deve ser um objeto Endereco")
	        .isInstanceOf(Endereco.class);
        
	    assertThat(enderecoSalvo.getCep())
	        .withFailMessage("O CEP do endereço deve ser igual ao informado")
	        .isEqualTo(49300000L);
	        
	    assertThat(enderecoSalvo.getRua())
	        .withFailMessage("A Rua do endereço deve ser igual a informada")
	        .isEqualTo("Rua a");
	        
	    assertThat(enderecoSalvo.getBairro())
	        .withFailMessage("O Bairro do endereço deve ser igual ao informado")
	        .isEqualTo("Macaé");
	        
	    assertThat(enderecoSalvo.getCidade())
	        .withFailMessage("A Cidade do endereço deve ser igual a informada")
	        .isEqualTo("Tobias Barreto");
	        
	    assertThat(enderecoSalvo.getEstado())
	        .withFailMessage("O Estado do endereço deve ser igual ao informado")
	        .isEqualTo("SE"); 
	}
	
	  @Test
	    public void teste_update_enderecos(){

	        enderecoRepositoryJPA.save(endereco);
	        
	        endereco.setCep(55555555L);
	        endereco.setCidade("Lagarto");
	        endereco.setEstado("Sergipe");
	        Endereco enderecoAtualizado = enderecoRepositoryJPA.update(endereco);

	        assertThat(enderecoAtualizado)
	            .withFailMessage("O retorno do método update não pode ser nullo")
	            .isNotNull();
	            
	        assertThat(enderecoAtualizado.getCep())
	            .withFailMessage("O CEP atualizado do endereço deve ser igual ao informado")
	            .isEqualTo(55555555L);
	            
	        assertThat(enderecoAtualizado.getCidade())
	            .withFailMessage("A Cidade atualizada do endereço deve ser igual a informada")
	            .isEqualTo("Lagarto");
	            
	        assertThat(enderecoAtualizado.getEstado())
	            .withFailMessage("O Estado atualizado do endereço deve ser igual ao informado")
	            .isEqualTo("Sergipe");    
	    
	    }
	  
	    @Test
	    public void teste_delete_enderecos(){

	        enderecoRepositoryJPA.save(endereco);
	        
	        enderecoRepositoryJPA.delete(endereco.getId());

	        Optional<Endereco> enderecoDeletado = enderecoRepositoryJPA.getById(endereco.getId());

	        assertThat(enderecoDeletado.isEmpty())
	            .withFailMessage("O retorno do método delete deve ser nullo")
	            .isEqualTo(true);     
	        
	    }

}
