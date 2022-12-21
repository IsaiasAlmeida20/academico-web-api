package br.com.academico.professor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/professores")
@Tag(name = "Professor")
public class ProfessorResource {

    @Inject
    @Named("professorservicedefaut")
    private IProfessorService professorService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Listar Professores",
        description = "Recupera uma lista completa de professores com todos os dados"
    )
    public Response recuperar() {
        List<Professor> listProfessores = new ArrayList<Professor>();
        try {
            listProfessores = professorService.listar();
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }
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
        Professor professor;
        try {
            professor = professorService.recuperar(matricula);
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }
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
        int matricula;
        try {
            matricula = professorService.criar(professor);
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }
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
        try {
            professor = professorService.atualizar(matricula, professor);
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }
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
        try {
            professorService.deletar(matricula);
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }
        return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
    }
}
