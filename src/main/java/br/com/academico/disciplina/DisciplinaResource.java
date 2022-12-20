package br.com.academico.disciplina;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
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

@Path("/disciplinas")
@Tag(name = "Disciplina")
public class DisciplinaResource {

    @Inject
    @Named("disciplinaservicedefaut")
    private IDisciplinaService disciplinaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Listar Disciplinas",
        description = "Recupera uma lista completa de disciplinas com todos os dados"
    )
    public Response recuperar() {
        List<Disciplina> listDisciplinas = new ArrayList<Disciplina>();
        try {
            listDisciplinas = disciplinaService.listar();
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }
        return Response.ok(listDisciplinas, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Recuperar Disciplina",
        description = "Recupera apenas uma disciplina a partir do seu id"
    )
    public Response recuperarId(@PathParam("id") int id) {
        Disciplina disciplina;
        try {
            disciplina = disciplinaService.recuperar(id);
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }
        return Response.ok(disciplina, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Criar uma displina",
        description = "Criar uma disciplina completa"
    )
    public Response inserir(@Valid Disciplina disciplina) {
        int id;
        try {
            id = disciplinaService.criar(disciplina);
            disciplina.setId(id);
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }
        return Response
                    .status(Response.Status.CREATED)
                    .entity(disciplina)
                    .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Atualiza uma disciplina",
        description = "Atualiza uma disciplina"
    )
    public Response atualizar(@PathParam("id") int id, Disciplina disciplina) {
        try {
            disciplina = disciplinaService.atualizar(id, disciplina);
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
        summary = "Deletar disciplina",
        description = "Deleta apenas uma disciplina a partir do seu id"
    )
    public Response deletar(@PathParam("id") int id) {
        try {
            disciplinaService.deletar(id);
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
