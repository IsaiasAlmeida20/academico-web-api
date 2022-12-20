package br.com.academico.aluno;

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

public class AlunoResourceTest extends JerseyTest {
    
    @Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(AlunoResource.class)
            .property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true)
            .register(AcademicoExceptionMapper.class)
            .register(AutoScanIoCFeature.class);
	}

    @Test
    public void teste_recuperar_lista_alunos() {
    
        Response response = target("/alunos").request().get();

        List<Aluno> listAlunos = response.readEntity(new GenericType<List<Aluno>>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals("O tipo de conteúdo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertTrue("O conteúdo da resposta deve ser uma lista: ", listAlunos instanceof List<?> );
    }
    
    @Test
    public void test_recuperar_aluno_por_maricula() {
        Response response = target("/alunos/20226699").request().get();
        Aluno aluno = response.readEntity(new GenericType<Aluno>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals("O tipo de conteúdo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertTrue("O conteúdo da resposta deve ser um Aluno: ", aluno instanceof Aluno);
    }

    @Test
    public void teste_criar_aluno() {
        String alunoJSON = Json.createObjectBuilder()
            .add("nome", "Isaias")
            .add("sobrenome", "Almeida")
            .add("idade", 24)
            .add("naturalidade", "SE")
            .add("sexo", 'M')
            .add("cpf", "777.777.777-77")
            .add("curso", "Informatica")
            .add("estaMatriculado", true)
            .add("Endereco", Json.createObjectBuilder()
                .add("CEP", 49000)
                .add("bairro", "Centro")
                .add("cidade", "Tobias")
                .add("estado", "Sergipe")
                .add("rua", "Rua A")
                .build()
            )
            .add("notas", Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                    .add("nota 1", 10.0)
                )
                .add(Json.createObjectBuilder()
                    .add("nota 2", 8.0)
                )
                .add(Json.createObjectBuilder()
                    .add("nota 3", 7.0)
                )
                .build()
            )
            .build()
            .toString();

        Response response = target("/alunos").request().post(Entity.json(alunoJSON));
        Aluno aluno = response.readEntity(new GenericType<Aluno>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 201: ", Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals("O tipo de conteúdo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertTrue("O conteúdo da resposta deve ser um Aluno: ", aluno instanceof Aluno);
    }

    @Test
    public void teste_atualizar_aluno_por_matricula() {
        String alunoJSON = Json.createObjectBuilder()
        .add("nome", "Isaias")
        .add("sobrenome", "Almeida")
        .add("idade", 24)
        .add("naturalidade", "SE")
        .add("sexo", 'M')
        .add("cpf", "777.777.777-77")
        .add("curso", "Informatica")
        .add("estaMatriculado", true)
        .add("Endereco", Json.createObjectBuilder()
            .add("CEP", 49000)
            .add("bairro", "Centro")
            .add("cidade", "Tobias")
            .add("estado", "Sergipe")
            .add("rua", "Rua A")
            .build()
        )
        .add("notas", Json.createArrayBuilder()
            .add(Json.createObjectBuilder()
                .add("nota 1", 10.0)
            )
            .add(Json.createObjectBuilder()
                .add("nota 2", 8.0)
            )
            .add(Json.createObjectBuilder()
                .add("nota 3", 7.0)
            )
            .build()
        )
        .build()
        .toString();

        Response response = target("/alunos/20229988").request().put(Entity.json(alunoJSON));
        
        response.readEntity(new GenericType<Aluno>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 204: ", Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    public void teste_deletar_aluno_por_matricula() {
        Response response = target("/alunos/20229977").request().delete();

        assertEquals("O codigo de status HTTP da resposta deve ser 204: ", Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }
}
