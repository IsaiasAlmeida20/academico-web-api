package br.com.academico.turma;

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

@Path("/turmas")
@Tag(name = "Turma")
public class TurmaResource {

    @Inject
    @Named("turmaservicedefaut")
    private ITurmaService turmaService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Listar Turmas",
        description = "Recupera uma lista completa de Turmas com todos os dados"
    )
    public Response recuperar() {
        List<Turma> listTurma = new ArrayList<Turma>();
        try {
            listTurma = turmaService.listar();
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }
        return Response.ok(listTurma, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Recuperar Turma",
        description = "Recupera apenas uma turma a partir do seu id"
    )
    public Response recuperarId(@PathParam("id") int id) {
        Turma turma;
        try {
            turma = turmaService.recuperar(id);
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }
        return Response.ok(turma, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Criar uma Turma",
        description = "Criar uma Turma completa"
    )
    public Response inserir(Turma turma) {
        int id;
        try {
            id = turmaService.criar(turma);
            turma.setId(id);
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }
        return Response
                    .status(Response.Status.CREATED)
                    .entity(turma)
                    .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Atualiza uma Turma",
        description = "Atualiza uma Turma"
    )
    public Response atualizar(@PathParam("id") int id, Turma turma) {
        try {
            turma = turmaService.atualizar(id, turma);
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
    @Path("/{id}")
    @Operation(
        summary = "Deletar Turma",
        description = "Deleta apenas uma Turma a partir do seu id"
    )
    public Response deletar(@PathParam("id") int id) {
        try {
            turmaService.deletar(id);
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
