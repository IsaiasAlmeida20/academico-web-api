package br.com.academico.endereco;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Before;
import org.junit.Test;

import br.com.academico.exception.AcademicoExceptionMapper;

public class EnderecoResourceTest extends JerseyTest {
	
	private IEnderecoService enderecoServiceMocked;
	
	private Endereco endereco;
	private Long idEndereco;

	@Before
	public void init() {
		endereco = new Endereco(49300000L, "Rua a", "Macaé", "Tobias Barreto", "SE");
		idEndereco = 1L;
	}
    
    @Override
	protected Application configure() {
    	enderecoServiceMocked = mock(IEnderecoService.class);
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig()
            .property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true)
            .register(AcademicoExceptionMapper.class)
            .register(new EnderecoResource(enderecoServiceMocked));
	}

    @Test
    public void teste_recuperar_lista_enderecos() {
    
    	List<Endereco> listEnderecoEsperada;
        listEnderecoEsperada = new ArrayList<Endereco>();
        listEnderecoEsperada.add(endereco);
        listEnderecoEsperada.add(endereco);

        given(enderecoServiceMocked.listar()).willReturn(listEnderecoEsperada);

        Response response = target("/enderecos").request().get();
        List<Endereco> listEnderecoResposta = response.readEntity(new GenericType<List<Endereco>>() {});

        assertThat(response.getStatus())
            .withFailMessage("O codigo de status HTTP da resposta deve ser 200")
            .isEqualTo(Status.OK.getStatusCode());  

        assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
            .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser JSON")
            .isEqualTo(MediaType.APPLICATION_JSON);

        assertThat(listEnderecoResposta)
            .withFailMessage("As Listas devem ter o mesmo tamanho")
            .hasSameSizeAs(listEnderecoEsperada);
        
    }
    
    @Test
    public void test_recuperar_endereco_por_id() {
    	
        endereco.setId(idEndereco);
       
        given(enderecoServiceMocked.recuperar(idEndereco)).willReturn(endereco);

        Response response = target("/enderecos/{id}")
            .resolveTemplate("id", idEndereco)
            .request().get();

        Endereco enderecoResposta = response.readEntity(new GenericType<Endereco>() {});
 
        assertThat(response.getStatus())
             .withFailMessage("O codigo de status HTTP da resposta deve ser 200")
             .isEqualTo(Status.OK.getStatusCode());  
 
        assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
             .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser JSON")
             .isEqualTo(MediaType.APPLICATION_JSON);
 
        assertThat(enderecoResposta)
             .withFailMessage("O conteúdo da resposta deve ser um objeto do tipo Endereço")
             .isInstanceOf(Endereco.class);
        
    }

    @Test
    public void teste_criar_endereco() {
    	
         endereco.setId(idEndereco);
         
         given(enderecoServiceMocked.criar(endereco)).willReturn(idEndereco);

         Response response = target("/enderecos").request().post(Entity.json(endereco));

         Endereco enderecoSalvo = response.readEntity(new GenericType<Endereco>() {});

         assertThat(response.getStatus())
             .withFailMessage("O codigo de status HTTP da resposta deve ser 201")
             .isEqualTo(Status.CREATED.getStatusCode());  

         assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
             .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser JSON")
             .isEqualTo(MediaType.APPLICATION_JSON);

         assertThat(enderecoSalvo)
             .withFailMessage("O endereço salvo não pode ser nullo")
             .isNotNull();   

         assertThat(enderecoSalvo)
             .withFailMessage("O conteúdo da resposta deve ser um objeto do tipo Endereço")
             .isInstanceOf(Endereco.class);
         
    }

    @Test
    public void teste_atualizar_endereco_por_id() {
    	
        endereco.setId(idEndereco);
         
        given(enderecoServiceMocked.atualizar(idEndereco, endereco)).willReturn(endereco);

        Response response = target("/enderecos/{id}")
            .resolveTemplate("id", idEndereco)
            .request()
            .put(Entity.json(endereco));

        assertThat(response.getStatus())
            .withFailMessage("codigo de status HTTP da resposta deve ser 204")
            .isEqualTo(Status.NO_CONTENT.getStatusCode());
        
    }

    @Test
    public void teste_deletar_endereco_por_id() {
    	
    	given(enderecoServiceMocked.deletar(idEndereco)).willReturn(idEndereco);

        Response response = target("/enderecos/{id}")
            .resolveTemplate("id", idEndereco)
            .request()
            .delete();
        
        assertThat(response.getStatus())
            .withFailMessage("codigo de status HTTP da resposta deve ser 204")
            .isEqualTo(Status.NO_CONTENT.getStatusCode());
        
    }

    @Test
    public void teste_atlerar_status_endereco_por_id() {
    	
    	endereco.setId(idEndereco);
        endereco.setStatus(EnderecoEnum.ATIVO);

        given(enderecoServiceMocked.mudarStatus(idEndereco, endereco.getStatus())).willReturn(endereco);

        Response response = target("/enderecos/{id}/status")
            .resolveTemplate("id", idEndereco)
            .request()
            .put(Entity.entity(endereco.getStatus().toString(), MediaType.TEXT_PLAIN));

        Endereco enderecoComStatusAlterado = response.readEntity(new GenericType<Endereco>() {});

        assertThat(response.getStatus())
            .withFailMessage("O codigo de status HTTP da resposta deve ser 200")
            .isEqualTo(Status.OK.getStatusCode());

        assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
            .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser JSON")
            .isEqualTo(MediaType.APPLICATION_JSON);    

        assertThat(enderecoComStatusAlterado)
            .withFailMessage("O endereço com status alterado não pode ser nullo")
            .isNotNull();   

        assertThat(enderecoComStatusAlterado)
            .withFailMessage("O conteúdo da resposta deve ser um objeto do tipo Endereço")
            .isInstanceOf(Endereco.class);
        
    }

    @Test
    public void test_criar_endereco_sem_rua() {
    	
    	endereco.setId(idEndereco);
    	endereco.setRua(null);
        
        given(enderecoServiceMocked.criar(endereco)).willReturn(idEndereco);

        Response response = target("/enderecos").request().post(Entity.json(endereco));

        String msg = response.readEntity(String.class); 

        assertThat(response.getStatus())
            .withFailMessage("O codigo de status HTTP da resposta deve ser 422")
            .isEqualTo(422);  

        assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
            .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser texto plano")
            .isEqualTo(MediaType.TEXT_PLAIN);

        assertThat(msg)
            .withFailMessage("O conteúdo da resposta deve conter uma mensagem de validação pré-definida")
            .contains("O atributo rua não pode ser nulo nem vazio.");
        
    }

    @Test
    public void teste_criar_endereco_rua_tamanho_invalido() {
    	
    	endereco.setId(idEndereco);
    	endereco.setRua("Rua");
        
        given(enderecoServiceMocked.criar(endereco)).willReturn(idEndereco);
 
        Response response = target("/enderecos").request().post(Entity.json(endereco));
 
        String msg = response.readEntity(String.class); 

        assertThat(response.getStatus())
            .withFailMessage("O codigo de status HTTP da resposta deve ser 422")
            .isEqualTo(422);  

        assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
            .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser texto plano")
            .isEqualTo(MediaType.TEXT_PLAIN);

        assertThat(msg)
            .withFailMessage("O conteúdo da resposta deve conter uma mensagem de validação pré-definida")
            .contains("O atributo rua deve conter no mínimo 5 e no máximo 50 caracteres."); 
        
    }

    @Test
    public void teste_criar_endereco_com_cep_invalido() {
    	
    	endereco.setId(idEndereco);
    	endereco.setCep(87776L);
        
        given(enderecoServiceMocked.criar(endereco)).willReturn(idEndereco);
  
        Response response = target("/enderecos").request().post(Entity.json(endereco));
  
        String msg = response.readEntity(String.class); 
 
        assertThat(response.getStatus())
            .withFailMessage("O codigo de status HTTP da resposta deve ser 422")
            .isEqualTo(422);  
 
        assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
            .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser texto plano")
            .isEqualTo(MediaType.TEXT_PLAIN);
 
        assertThat(msg)
            .withFailMessage("O conteúdo da resposta deve conter uma mensagem de validação pré-definida")
            .contains("O atributo CEP deve ser inteiro e ter no mínimo 8 algarismos.");
        
    }

}
