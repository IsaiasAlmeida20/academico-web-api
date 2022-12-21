package br.com.academico.turma;

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

public class TurmaResourceTest extends JerseyTest{
    
    @Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(TurmaResource.class)
            .property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true)
            .register(AcademicoExceptionMapper.class)
            .register(AutoScanIoCFeature.class);
	}
    
    @Test
    public void teste_recuperar_lista_turmas() {
    
        Response response = target("/turmas").request().get();

        List<Turma> listTurmas = response.readEntity(new GenericType<List<Turma>>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals("O tipo de conteúdo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertTrue("O conteúdo da resposta deve ser uma lista: ", listTurmas instanceof List<?> );
    }
    
    @Test
    public void test_recuperar_turma_por_id() {
        Response response = target("/turmas/20").request().get();
        Turma turma = response.readEntity(new GenericType<Turma>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals("O tipo de conteúdo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertTrue("O conteúdo da resposta deve ser um Aluno: ", turma instanceof Turma);
    }

    @Test
    public void teste_criar_turma() {
        String turmaJSON = Json.createObjectBuilder()
            .add("nome", "Informatica")
            .add("periodo", "Primeiro")
            .add("professor", Json.createObjectBuilder()
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
            )
            .add("disciplina", Json.createObjectBuilder()
                .add("nomeDisciplina", "0000-PROGRAMACAO")
                .add("cargaHoraria", 200)
            )
            .add("alunos", Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
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
                )
            )
            .add("salas", Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                    .add("numeroSala", 4)
                    .add("capacidadeAlunos", 30)
                    .add("possuiArcondicionado", true)
                    .add("quadroBranco", true)
                    .add("laboratorio", false)
                )
            )
            .build()
            .toString();

        Response response = target("/turmas").request().post(Entity.json(turmaJSON));
        Turma turma = response.readEntity(new GenericType<Turma>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 201: ", Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals("O tipo de conteúdo HTTP da resposta deve ser JSON: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertTrue("O conteúdo da resposta deve ser um Aluno: ", turma instanceof Turma);
    }

    @Test
    public void teste_atualizar_turma_por_id() {
        String turmaJSON = Json.createObjectBuilder()
            .add("nome", "Informatica")
            .add("periodo", "Primeiro")
            .add("professor", Json.createObjectBuilder()
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
            )
            .add("disciplina", Json.createObjectBuilder()
                .add("nomeDisciplina", "0000-PROGRAMACAO")
                .add("cargaHoraria", 200)
            )
            .add("alunos", Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
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
                )
            )
            .add("salas", Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                    .add("numeroSala", 4)
                    .add("capacidadeAlunos", 30)
                    .add("possuiArcondicionado", true)
                    .add("quadroBranco", true)
                    .add("laboratorio", false)
                )
            )
            .build()
            .toString();

        Response response = target("/turmas/20").request().put(Entity.json(turmaJSON));
        
        response.readEntity(new GenericType<Turma>() {});

        assertEquals("O codigo de status HTTP da resposta deve ser 204: ", Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    public void teste_deletar_turma_por_id() {
        Response response = target("/turmas/20").request().delete();

        assertEquals("O codigo de status HTTP da resposta deve ser 204: ", Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }
}
