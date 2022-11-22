package br.com.academico.disciplina;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.json.Json;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

public class DisciplinaResourceTest extends JerseyTest {

    @Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(DisciplinaResource.class);
	}

    @Test
    public void teste_recuperar_lista_disciplinas() {

        Response response = target("/disciplinas").request().get();

        List<Disciplina> listDisciplinas = response.readEntity(new GenericType<List<Disciplina>>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals("O tipo de conteúdo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertTrue("O conteúdo da resposta deve ser uma lista: ", listDisciplinas instanceof List<?> );
    }

    @Test
    public void test_recuperar_disciplina_por_id() {
        Response response = target("/disciplinas/999").request().get();
        Disciplina disciplina = response.readEntity(new GenericType<Disciplina>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals("O tipo de conteúdo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertTrue("O conteúdo da resposta deve ser uma Disciplina: ", disciplina instanceof Disciplina);
    }

    @Test
    public void teste_criar_disciplina() {
        String disciplinaJSON = Json.createObjectBuilder()
            .add("nomeDisciplina", "Programação")
            .add("cargaHoraria", 200)
            .build()
            .toString();

        Response response = target("/disciplinas").request().post(Entity.json(disciplinaJSON));
        Disciplina disciplina = response.readEntity(new GenericType<Disciplina>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 201: ", Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals("O tipo de conteúdo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertTrue("O conteúdo da resposta deve ser uma Disciplina: ", disciplina instanceof Disciplina);
    }

    @Test
    public void teste_atualizar_disciplina_por_id() {
        String disciplinaJSON = Json.createObjectBuilder()
            .add("nomeDisciplina", "CS1")
            .add("cargaHoraria", 300)
            .build()
            .toString();

        Response response = target("/disciplinas/223").request().put(Entity.json(disciplinaJSON));
        
        response.readEntity(new GenericType<Disciplina>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 204: ", Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    public void teste_deletar_disciplina_por_id() {
        Response response = target("/disciplinas/133").request().delete();

        assertEquals("O codigo de status HTTP da resposta deve ser 204: ", Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }
}
