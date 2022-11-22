package br.com.academico.sala;

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

public class SalaResourceTest extends JerseyTest{
    
    @Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(SalaResource.class);
	}

    @Test
    public void teste_recuperar_lista_salas() {
    
        Response response = target("/salas").request().get();

        List<Sala> listSalas = response.readEntity(new GenericType<List<Sala>>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals("O tipo de conteúdo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertTrue("O conteúdo da resposta deve ser uma lista: ", listSalas instanceof List<?> );
    }
    
    @Test
    public void test_recuperar_sala_por_id() {
        Response response = target("/salas/999").request().get();
        Sala sala = response.readEntity(new GenericType<Sala>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals("O tipo de conteúdo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertTrue("O conteúdo da resposta deve ser um Sala: ", sala instanceof Sala);
    }

    @Test
    public void teste_criar_sala() {
        String salaJSON = Json.createObjectBuilder()
            .add("numeroSala", 4)
            .add("capacidadeAlunos", 30)
            .add("possuiArcondicionado", true)
            .add("quadroBranco", true)
            .add("laboratorio", false)
            .build()
            .toString();

        Response response = target("/salas").request().post(Entity.json(salaJSON));
        Sala sala = response.readEntity(new GenericType<Sala>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 201: ", Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals("O tipo de conteúdo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertTrue("O conteúdo da resposta deve ser um Endereço: ", sala instanceof Sala);
    }

    @Test
    public void teste_atualizar_sala_por_id() {
        String salaJSON = Json.createObjectBuilder()
            .add("numeroSala", 5)
            .add("capacidadeAlunos", 40)
            .add("possuiArcondicionado", true)
            .add("quadroBranco", true)
            .add("laboratorio", true)
            .build()
            .toString();

        Response response = target("/salas/123").request().put(Entity.json(salaJSON));
        
        response.readEntity(new GenericType<Sala>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 204: ", Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    public void teste_deletar_sala_por_id() {
        Response response = target("/salas/123").request().delete();

        assertEquals("O codigo de status HTTP da resposta deve ser 204: ", Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }
}
