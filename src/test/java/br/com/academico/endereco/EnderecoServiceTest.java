package br.com.academico.endereco;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class EnderecoServiceTest {
    
    private EnderecoService enderecoService;
    
    private IEnderecoRepository enderecoRepositoryMocked;
    
    private Endereco endereco;
    
    private Long idEndereco;

    @Before
    public void init() {
        enderecoRepositoryMocked = mock(IEnderecoRepository.class);
        enderecoService = new EnderecoService(enderecoRepositoryMocked);
        endereco = new Endereco(49300000L, "Rua a", "Macaé", "Tobias Barreto", "SE");
        idEndereco = 1L;
    }

    @Test
    public void teste_recuperar_lista_enderecos() {

        List<Endereco> listEnderecoEsperada;
        listEnderecoEsperada = new ArrayList<Endereco>();
        listEnderecoEsperada.add(endereco);
        listEnderecoEsperada.add(endereco);

        given(enderecoRepositoryMocked.findAll()).willReturn(listEnderecoEsperada);

        List<Endereco> listEnderecoResposta = enderecoService.listar();

        assertThat(listEnderecoResposta)
            .withFailMessage("O retorno do método listar deve ser uma lista de endereços")
            .isInstanceOf(List.class);

        assertThat(listEnderecoResposta)
            .withFailMessage("O retorno do método listar deve ser uma lista de endereços não nulla")
            .isNotNull();

        assertThat(listEnderecoResposta.size())
            .withFailMessage("O retorno do método listar deve ser uma lista de endereços com dois endereços")
            .isEqualTo(2);

    }

    @Test
    public void test_recuperar_endereco_por_id() {
    	  endereco.setId(idEndereco);

          given(enderecoRepositoryMocked.getById(idEndereco)).willReturn(Optional.of(endereco));

          Endereco enderecoResposta = enderecoService.recuperar(idEndereco);

          assertThat(enderecoResposta)
              .withFailMessage("O retorno do método recuperar não pode ser nullo")
              .isNotNull();     

          assertThat(enderecoResposta)
              .withFailMessage("O retorno do método recuperar deve ser um objeto Endereco")
              .isInstanceOf(Endereco.class);

    }

    @Test
    public void teste_criar_endereco() {
    	
        endereco.setId(idEndereco);

        given(enderecoRepositoryMocked.save(endereco)).willReturn(endereco);

        Long idEnderecoSalvo = enderecoService.criar(endereco);

        assertThat(idEnderecoSalvo)
            .withFailMessage("O retorno do método criar não pode ser um ID nullo")
            .isNotNull();   

        assertThat(idEnderecoSalvo)
            .withFailMessage("O retorno do método criar deve ser um ID de um Endereco criado")
            .isInstanceOf(Long.class);
    }

    @Test
    public void teste_atualizar_endereco_por_id(){
    	
        endereco.setId(idEndereco);

        given(enderecoRepositoryMocked.getById(endereco.getId()))
            .willReturn(Optional.of(endereco));

        given(enderecoRepositoryMocked.update(endereco))
            .willAnswer(invocation -> invocation.getArgument(0));

        Endereco enderecoAtualizado = enderecoService.atualizar(endereco.getId(), endereco);

        assertThat(enderecoAtualizado)
            .withFailMessage("O retorno do método atualizar não pode ser um Endereço nullo")
            .isNotNull();   

        assertThat(enderecoAtualizado)
            .withFailMessage("O retorno do método atualizar deve ser um objeto Endereco")
            .isInstanceOf(Endereco.class);
    }

    @Test
    public void teste_deletar_endereco_por_id(){
    	
        endereco.setId(idEndereco);

        given(enderecoRepositoryMocked.getById(idEndereco))
            .willReturn(Optional.of(endereco));

        willDoNothing().given(enderecoRepositoryMocked).delete(idEndereco);

        Long idEnderecoDeletado = enderecoService.deletar(idEndereco);

        assertThat(idEnderecoDeletado)
            .withFailMessage("O retorno do método deletar não pode ser um ID nullo")
            .isNotNull();   

        assertThat(idEnderecoDeletado)
            .withFailMessage("O retorno do método deletar deve ser um ID de um Endereco deletado")
            .isInstanceOf(Long.class);
    }


	@Test
    public void teste_atlerar_status_endereco_por_id(){
		
        endereco.setId(idEndereco);
        endereco.setStatus(EnderecoEnum.ATIVO);

        given(enderecoRepositoryMocked.getById(endereco.getId()))
            .willReturn(Optional.of(endereco));

        given(enderecoRepositoryMocked.update(endereco))
            .willAnswer(invocation -> invocation.getArgument(0));

        Endereco enderecoComStatusAlterado = enderecoService.mudarStatus(endereco.getId(), endereco.getStatus());

        assertThat(enderecoComStatusAlterado)
            .withFailMessage("O retorno do método mudar status não pode ser nullo")
            .isNotNull();     

        assertThat(enderecoComStatusAlterado)
            .withFailMessage("O retorno do método mudar status deve ser um objeto Endereco")
            .isInstanceOf(Endereco.class);
        
    }

    @Test 
    public void teste_atlerar_status_endereco_por_id_inexistente() {
    
        endereco.setId(idEndereco);

        given(enderecoRepositoryMocked.getById(endereco.getId()))
            .willReturn(Optional.empty());
        Exception exception = assertThrows(EnderecoNaoExisteException.class, () -> {
	        enderecoService.recuperar(endereco.getId());
	    });

	    assertThat(exception.getMessage())
	        .withFailMessage("Menssagem de execeção deve ser lançada e ser compatível com a esperada")
	        .contains("O endereço não existe na base de dados.");
	    
    }
    

    @Test
    public void teste_atualizar_endereco_por_id_inexistente() {
    	 Long idEndereco = 10L;
         Endereco enderecoEnviado = new Endereco(49000000L,"Rua A", "Bairro A", "Cidade A", "Estado A");
         enderecoEnviado.setId(idEndereco);

         given(enderecoRepositoryMocked.getById(enderecoEnviado.getId()))
             .willReturn(Optional.empty());

        Exception exception = assertThrows(EnderecoNaoExisteException.class, () -> {
        	enderecoService.atualizar(enderecoEnviado.getId(), enderecoEnviado);
        });

        assertThat(exception.getMessage())
            .withFailMessage("Mensagem de execeção deve ser lançada e ser compatível com a esperada")
            .contains("O endereço não existe na base de dados.");
    }

}



