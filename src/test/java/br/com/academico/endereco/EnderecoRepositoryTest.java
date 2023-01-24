package br.com.academico.endereco;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EnderecoRepositoryTest {

	private EntityManagerFactory emf;

	private EntityManager em;

	private IEnderecoRepository enderecoRepositoryJPA;

	@Before
	public void init() {
		emf = Persistence.createEntityManagerFactory("academico-pu");
		em = emf.createEntityManager();
		enderecoRepositoryJPA = new EnderecoRepositoryJPA(em);
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
	public void test_save_endereco() {
		
		Endereco enderecoEnvido = new Endereco(49300000L, "Rua a", "Macaé", "Tobias Barreto", "SE");
		
		Endereco enderecoSalvo = enderecoRepositoryJPA.save(enderecoEnvido);
		
		assertThat(enderecoSalvo)
			.withFailMessage("O retorno do método não deve ser nullo")
			.isNull();
	}

}
