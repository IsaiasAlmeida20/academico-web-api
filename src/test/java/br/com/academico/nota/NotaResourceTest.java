package br.com.academico.nota;

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

public class NotaResourceTest extends JerseyTest{

    private INotaService notaServiceMocked;

    private Nota nota;

    private Long idNota;

    @Before
    public void init() {
        nota = new Nota(8.0, 2);
		nota.setMatricula(20239999L);
        idNota = 1L;
    }
    
    @Override
	protected Application configure() {
        notaServiceMocked = mock(INotaService.class);
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig()
            .property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, NotaResource.class)
            .register(AcademicoExceptionMapper.class)
            .register(new NotaResource(notaServiceMocked));
	}

    @Test
    public void teste_recuperar_lista_notas() {
    
        List<Nota> listNotaEsperada;
        listNotaEsperada = new ArrayList<Nota>();
        listNotaEsperada.add(nota);
        listNotaEsperada.add(nota);

        given(notaServiceMocked.listar()).willReturn(listNotaEsperada);

        Response response = target("/notas").request().get();
        List<Nota> listNotaResposta = response.readEntity(new GenericType<List<Nota>>() {});

        assertThat(response.getStatus())
            .withFailMessage("O codigo de status HTTP da resposta deve ser 200")
            .isEqualTo(Status.OK.getStatusCode());  

        assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
            .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser JSON")
            .isEqualTo(MediaType.APPLICATION_JSON);

        assertThat(listNotaResposta)
            .withFailMessage("As Listas devem ter o mesmo tamanho")
            .hasSameSizeAs(listNotaEsperada);
    }
    
    @Test
    public void test_recuperar_nota_por_id() {

        nota.setId(idNota);
       
        given(notaServiceMocked.recuperar(idNota)).willReturn(nota);

        Response response = target("/notas/{id}")
            .resolveTemplate("id", idNota)
            .request().get();

            Nota notaResposta = response.readEntity(new GenericType<Nota>() {});
 
        assertThat(response.getStatus())
             .withFailMessage("O codigo de status HTTP da resposta deve ser 200")
             .isEqualTo(Status.OK.getStatusCode());  
 
        assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
             .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser JSON")
             .isEqualTo(MediaType.APPLICATION_JSON);
 
        assertThat(notaResposta)
             .withFailMessage("O conteúdo da resposta deve ser um objeto do tipo Nota")
             .isInstanceOf(Nota.class);
    }

    @Test
    public void teste_criar_sala() {
        nota.setId(idNota);
        
        given(notaServiceMocked.criar(nota)).willReturn(idNota);

        Response response = target("/notas").request().post(Entity.json(nota));

        Nota notaSalva = response.readEntity(new GenericType<Nota>() {});

        assertThat(response.getStatus())
            .withFailMessage("O codigo de status HTTP da resposta deve ser 201")
            .isEqualTo(Status.CREATED.getStatusCode());  

        assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
            .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser JSON")
            .isEqualTo(MediaType.APPLICATION_JSON);

        assertThat(notaSalva)
            .withFailMessage("A nota salva não pode ser nulla")
            .isNotNull();   

        assertThat(notaSalva)
            .withFailMessage("O conteúdo da resposta deve ser um objeto do tipo Nota")
            .isInstanceOf(Nota.class);
    }

    @Test
    public void teste_atualizar_sala_por_id() {
        nota.setId(idNota);
         
        given(notaServiceMocked.atualizar(idNota, nota)).willReturn(nota);

        Response response = target("/notas/{id}")
            .resolveTemplate("id", idNota)
            .request()
            .put(Entity.json(nota));

        assertThat(response.getStatus())
            .withFailMessage("codigo de status HTTP da resposta deve ser 204")
            .isEqualTo(Status.NO_CONTENT.getStatusCode());
        
    }

    @Test
    public void teste_deletar_sala_por_id() {
        given(notaServiceMocked.deletar(idNota)).willReturn(idNota);

        Response response = target("/notas/{id}")
            .resolveTemplate("id", idNota)
            .request()
            .delete();
        
        assertThat(response.getStatus())
            .withFailMessage("codigo de status HTTP da resposta deve ser 204")
            .isEqualTo(Status.NO_CONTENT.getStatusCode());
    }
}
