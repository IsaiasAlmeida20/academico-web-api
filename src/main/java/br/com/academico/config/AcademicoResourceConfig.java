package br.com.academico.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import br.com.academico.aluno.AlunoResource;
import br.com.academico.disciplina.DisciplinaResource;
import br.com.academico.endereco.EnderecoResource;

import br.com.academico.exception.AcademicoExceptionMapper;
import br.com.academico.nota.NotaResource;
import br.com.academico.professor.ProfessorResource;
import br.com.academico.sala.SalaResource;
import br.com.academico.turma.TurmaResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@ApplicationPath("/")
@OpenAPIDefinition(
    info =  @Info(
        title = "Academico Web API",
        version = "1.0",
        description = "WEB API utilozando o estilo arquitetural REST"
    ),
    servers = {
        @Server(
            description = "Desenvolvimento",
            url = "/academico-web-api"
        )
    }
)
public class AcademicoResourceConfig extends ResourceConfig {

    public AcademicoResourceConfig() {
        registrarEndPoints();
        configurarSwagger();
        configurarValidacao();
        configurarInversaoControle();
    }

    private void registrarEndPoints() {
        System.out.println("[Configurando as classes resources/endpoints da aplicação]");
        register(EnderecoResource.class);
        register(SalaResource.class);
        register(DisciplinaResource.class);
        register(AlunoResource.class);
        register(ProfessorResource.class);
        register(NotaResource.class);
        register(TurmaResource.class);
    }
    
    private void configurarSwagger() {
        System.out.println("[Configurando o Swagger | OPEN API]");
        OpenApiResource openApiResource = new OpenApiResource();
        register(openApiResource);
    }

    private void configurarValidacao() {
        System.out.println("[Configurando a Validação]");
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        register(AcademicoExceptionMapper.class);
    }

    private void configurarInversaoControle() {
        System.out.println("[Configurando a Inversão de controle (IoC)]");
        register(AutoScanIoCFeature.class);
    }
}
