package br.com.academico.sala;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
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

public class SalaResourceTest extends JerseyTest{

    private ISalaService salaServiceMocked;

    private Sala sala;

    private Long idSala;

    @Before
    public void init() {
        sala = new Sala(1, 30, true,true, false);
        idSala = 1L;
    }
    
    @Override
	protected Application configure() {
        salaServiceMocked = mock(ISalaService.class);
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig()
            .property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, SalaResource.class)
            .register(AcademicoExceptionMapper.class)
            .register(new SalaResource(salaServiceMocked));
	}

    @Test
    public void teste_recuperar_lista_salas() {
    
        List<Sala> listSalaEsperada;
        listSalaEsperada = new ArrayList<Sala>();
        listSalaEsperada.add(sala);
        listSalaEsperada.add(sala);

        given(salaServiceMocked.listar()).willReturn(listSalaEsperada);

        Response response = target("/salas").request().get();
        List<Sala> listSalaResposta = response.readEntity(new GenericType<List<Sala>>() {});

        assertThat(response.getStatus())
            .withFailMessage("O codigo de status HTTP da resposta deve ser 200")
            .isEqualTo(Status.OK.getStatusCode());  

        assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
            .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser JSON")
            .isEqualTo(MediaType.APPLICATION_JSON);

        assertThat(listSalaResposta)
            .withFailMessage("As Listas devem ter o mesmo tamanho")
            .hasSameSizeAs(listSalaEsperada);
    }
    
    @Test
    public void test_recuperar_sala_por_id() {

        sala.setId(idSala);
       
        given(salaServiceMocked.recuperar(idSala)).willReturn(sala);

        Response response = target("/salas/{id}")
            .resolveTemplate("id", idSala)
            .request().get();

        Sala salaResposta = response.readEntity(new GenericType<Sala>() {});
 
        assertThat(response.getStatus())
             .withFailMessage("O codigo de status HTTP da resposta deve ser 200")
             .isEqualTo(Status.OK.getStatusCode());  
 
        assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
             .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser JSON")
             .isEqualTo(MediaType.APPLICATION_JSON);
 
        assertThat(salaResposta)
             .withFailMessage("O conteúdo da resposta deve ser um objeto do tipo Sala")
             .isInstanceOf(Sala.class);
    }

    @Test
    public void teste_criar_sala() {
        sala.setId(idSala);
        
        given(salaServiceMocked.criar(sala)).willReturn(idSala);

        Response response = target("/salas").request().post(Entity.json(sala));

        Sala SalaSalva = response.readEntity(new GenericType<Sala>() {});

        assertThat(response.getStatus())
            .withFailMessage("O codigo de status HTTP da resposta deve ser 201")
            .isEqualTo(Status.CREATED.getStatusCode());  

        assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
            .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser JSON")
            .isEqualTo(MediaType.APPLICATION_JSON);

        assertThat(SalaSalva)
            .withFailMessage("O endereço salvo não pode ser nullo")
            .isNotNull();   

        assertThat(SalaSalva)
            .withFailMessage("O conteúdo da resposta deve ser um objeto do tipo Sala")
            .isInstanceOf(Sala.class);
    }

    @Test
    public void teste_atualizar_sala_por_id() {
        sala.setId(idSala);
         
        given(salaServiceMocked.atualizar(idSala, sala)).willReturn(sala);

        Response response = target("/salas/{id}")
            .resolveTemplate("id", idSala)
            .request()
            .put(Entity.json(sala));

        assertThat(response.getStatus())
            .withFailMessage("codigo de status HTTP da resposta deve ser 204")
            .isEqualTo(Status.NO_CONTENT.getStatusCode());
        
    }

    @Test
    public void teste_deletar_sala_por_id() {
        given(salaServiceMocked.deletar(idSala)).willReturn(idSala);

        Response response = target("/salas/{id}")
            .resolveTemplate("id", idSala)
            .request()
            .delete();
        
        assertThat(response.getStatus())
            .withFailMessage("codigo de status HTTP da resposta deve ser 204")
            .isEqualTo(Status.NO_CONTENT.getStatusCode());
    }
}
