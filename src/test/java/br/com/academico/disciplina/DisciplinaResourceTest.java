package br.com.academico.disciplina;

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

public class DisciplinaResourceTest extends JerseyTest {

    private IDisciplinaService disciplinaServiceMocked;

    private Disciplina disciplina;

    private Long idDisciplina;

    @Before
    public void init() {
        disciplina = new Disciplina("PROGRAMACAO", 120);
        idDisciplina = 1L;
    }

    @Override
	protected Application configure() {
        disciplinaServiceMocked = mock(IDisciplinaService.class);
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig()
            .property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true)
            .register(AcademicoExceptionMapper.class)
            .register(new DisciplinaResource(disciplinaServiceMocked));
	}

    @Test
    public void teste_recuperar_lista_disciplinas() {

        List<Disciplina> listDisciplinaEsperada = new ArrayList<Disciplina>();
        listDisciplinaEsperada.add(disciplina);
        listDisciplinaEsperada.add(disciplina);

        given(disciplinaServiceMocked.listar()).willReturn(listDisciplinaEsperada);

        Response response = target("/disciplinas").request().get();
        List<Disciplina> listDisciplinaResposta = response.readEntity(new GenericType<List<Disciplina>>() {});

        assertThat(response.getStatus())
            .withFailMessage("O codigo de status HTTP da resposta deve ser 200")
            .isEqualTo(Status.OK.getStatusCode());  

        assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
            .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser JSON")
            .isEqualTo(MediaType.APPLICATION_JSON);

        assertThat(listDisciplinaResposta)
            .withFailMessage("As Listas devem ter o mesmo tamanho")
            .hasSameSizeAs(listDisciplinaEsperada);
    }

    @Test
    public void test_recuperar_disciplina_por_id() {
        disciplina.setId(idDisciplina);
       
        given(disciplinaServiceMocked.recuperar(idDisciplina)).willReturn(disciplina);

        Response response = target("/disciplinas/{id}")
            .resolveTemplate("id", idDisciplina)
            .request().get();

        Disciplina dsiciplinaResposta = response.readEntity(new GenericType<Disciplina>() {});
 
        assertThat(response.getStatus())
             .withFailMessage("O codigo de status HTTP da resposta deve ser 200")
             .isEqualTo(Status.OK.getStatusCode());  
 
        assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
             .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser JSON")
             .isEqualTo(MediaType.APPLICATION_JSON);
 
        assertThat(dsiciplinaResposta)
             .withFailMessage("O conteúdo da resposta deve ser um objeto do tipo Dsiciplina")
             .isInstanceOf(Disciplina.class);
    }

    @Test
    public void teste_criar_disciplina() {
        disciplina.setId(idDisciplina);
        
        given(disciplinaServiceMocked.criar(disciplina)).willReturn(idDisciplina);

        Response response = target("/disciplinas").request().post(Entity.json(disciplina));

        Disciplina disciplinaSalva = response.readEntity(new GenericType<Disciplina>() {});

        assertThat(response.getStatus())
            .withFailMessage("O codigo de status HTTP da resposta deve ser 201")
            .isEqualTo(Status.CREATED.getStatusCode());  

        assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE))
            .withFailMessage("O tipo de conteúdo HTTP da resposta deve ser JSON")
            .isEqualTo(MediaType.APPLICATION_JSON);

        assertThat(disciplinaSalva)
            .withFailMessage("A dsiciplina salva não pode ser nulla")
            .isNotNull();   

        assertThat(disciplinaSalva)
            .withFailMessage("O conteúdo da resposta deve ser um objeto do tipo Endereço")
            .isInstanceOf(Disciplina.class);
    }

    @Test
    public void teste_atualizar_disciplina_por_id() {
        disciplina.setId(idDisciplina);
         
        given(disciplinaServiceMocked.atualizar(idDisciplina, disciplina)).willReturn(disciplina);

        Response response = target("/disciplinas/{id}")
            .resolveTemplate("id", idDisciplina)
            .request()
            .put(Entity.json(disciplina));

        assertThat(response.getStatus())
            .withFailMessage("codigo de status HTTP da resposta deve ser 204")
            .isEqualTo(Status.NO_CONTENT.getStatusCode());
    }

    @Test
    public void teste_deletar_disciplina_por_id() {
        given(disciplinaServiceMocked.deletar(idDisciplina)).willReturn(idDisciplina);

        Response response = target("/disciplinas/{id}")
            .resolveTemplate("id", idDisciplina)
            .request()
            .delete();
        
        assertThat(response.getStatus())
            .withFailMessage("codigo de status HTTP da resposta deve ser 204")
            .isEqualTo(Status.NO_CONTENT.getStatusCode());
        
    }

}
