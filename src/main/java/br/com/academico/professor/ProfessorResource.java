package br.com.academico.professor;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.academico.endereco.Endereco;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/professores")
@Tag(name = "Professor")
public class ProfessorResource {

    private Professor professor;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Listar Professores",
        description = "Recupera uma lista completa de professores com todos os dados"
    )
    public Response recuperar() {
        List<Professor> listProfessores = new ArrayList<Professor>();
        Professor p1 = new Professor("Jose", "Silva", 34, "BA", 'M', "777.777.777-77", 5.000, 40);
        Professor p2 = new Professor("Joao", "Barros", 44, "SE", 'M', "777.555.777-77", 5.700, 42);
        p1.setEndereco(new Endereco(49300000, "Rua A", "Centro", "Tobias", "SE"));
        p2.setEndereco(new Endereco(49400000, "Rua B", "Centro", "Lagarto", "BA"));
        listProfessores.add(p2);
        listProfessores.add(p1);
        return Response.ok(listProfessores, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{matricula}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Recuperar Professor",
        description = "Recupera apenas um professor a partir da sua matricula"
    )
    public Response recuperarMatricula(@PathParam("matricula") int matricula) {
        professor = new Professor("Jose", "Silva", 34, "BA", 'M', "777.777.777-77", 5.000, 40);
        professor.setMatricula(matricula);
        return Response.ok(professor, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Criar Professor",
        description = "Cria um professor completo"
    )
    public Response inserir(Professor professor) {
        professor.setMatricula(20221998);
        return Response
                    .status(Response.Status.CREATED)
                    .entity(professor)
                    .build();
    }

    @PUT
    @Path("/{matricula}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Atualiza um professor",
        description = "Atualiza um professor"
    )
    public Response atualizar(@PathParam("matricula") int matricula, Professor professor) {
        professor.setMatricula(20221998);
        return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
    }

    @DELETE
    @Path("/{matricula}")
    @Operation(
        summary = "Deletar professor",
        description = "Deleta apenas um professor a partir da sua matricula"
    )
    public Response deletar(@PathParam("matricula") int matricula) {
        return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
    }
}
