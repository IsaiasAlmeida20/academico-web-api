package br.com.academico.professor;

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
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import br.com.academico.config.AutoScanIoCFeature;
import br.com.academico.exception.AcademicoExceptionMapper;

public class ProfessorResourceTest extends JerseyTest{
    
    @Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(ProfessorResource.class)
            .property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true)
            .register(AcademicoExceptionMapper.class)
            .register(AutoScanIoCFeature.class);
	}

    @Test
    public void teste_recuperar_lista_professores() {
    
        Response response = target("/professores").request().get();

        List<Professor> listProfessores = response.readEntity(new GenericType<List<Professor>>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals("O tipo de conteúdo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertTrue("O conteúdo da resposta deve ser uma lista: ", listProfessores instanceof List<?> );
    }
    
    @Test
    public void test_recuperar_professor_por_matricula() {
        Response response = target("/professores/20227999").request().get();
        Professor professor = response.readEntity(new GenericType<Professor>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals("O tipo de conteúdo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertTrue("O conteúdo da resposta deve ser um Professor: ", professor instanceof Professor);
    }

    @Test
    public void teste_criar_professor() {
        String professorJSON = Json.createObjectBuilder()
            .add("nome", "Pedro")
            .add("sobrenome", "Paulo")
            .add("idade", 30)
            .add("naturalidade", "SE")
            .add("sexo", 'M')
            .add("cpf", "777.777.777-77")
            .add("salario", 5.000)
            .add("caragaHoraria", 48)
            .add("Endereco", Json.createObjectBuilder()
                .add("CEP", 49000)
                .add("bairro", "Centro")
                .add("cidade", "Aracaju")
                .add("estado", "Sergipe")
                .add("rua", "Rua da Feira")
            )
            .build()
            .toString();

        Response response = target("/professores").request().post(Entity.json(professorJSON));
        Professor professor = response.readEntity(new GenericType<Professor>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 201: ", Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals("O tipo de conteúdo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertTrue("O conteúdo da resposta deve ser um Professor: ", professor instanceof Professor);
    }

    @Test
    public void teste_atualizar_professor_por_matricula() {
        String professorJSON = Json.createObjectBuilder()
            .add("nome", "Pedro")
            .add("sobrenome", "Paulo")
            .add("idade", 30)
            .add("naturalidade", "SE")
            .add("sexo", 'M')
            .add("cpf", "777.777.777-77")
            .add("salario", 5.000)
            .add("caragaHoraria", 48)
            .add("Endereco", Json.createObjectBuilder()
                .add("CEP", 49000)
                .add("bairro", "Centro")
                .add("cidade", "Aracaju")
                .add("estado", "Sergipe")
                .add("rua", "Rua da Feira")
            )
            .build()
            .toString();

        Response response = target("/professores/20229988").request().put(Entity.json(professorJSON));
        
        response.readEntity(new GenericType<Professor>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 204: ", Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    public void teste_deletar_professor_por_id() {
        Response response = target("/professores/20229977").request().delete();

        assertEquals("O codigo de status HTTP da resposta deve ser 204: ", Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }
}
