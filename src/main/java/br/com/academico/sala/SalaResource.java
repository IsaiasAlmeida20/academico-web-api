package br.com.academico.sala;

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

@Path("/salas")
@Tag(name = "Sala")
public class SalaResource {

    private ISalaService salaService;
    
    @Inject
    
    public SalaResource(@Named("salaservicedefault") ISalaService salaService) {
        this.salaService = salaService;
    } 

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Listar Salas",
        description = "Recupera uma lista completa de salas com todos os dados"
    )
    public Response recuperar() {
        List<Sala> listSalas = new ArrayList<Sala>();
        try {
            listSalas = salaService.listar();
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }
        return Response.ok(listSalas, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Recuperar Sala",
        description = "Recupera apenas uma sala a partir do seu id"
    )
    public Response recuperarId(@PathParam("id") Long id) {
        Sala sala;
        try {
            sala = salaService.recuperar(id);
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }
        return Response.ok(sala, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Criar Sala",
        description = "Cria um sala completa"
    )
    public Response inserir(Sala sala) {
        Long id;
        try {
            id = salaService.criar(sala);
            sala.setId(id);
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }
        return Response
                    .status(Response.Status.CREATED)
                    .entity(sala)
                    .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Atualiza um sala",
        description = "Atualiza um sala"
    )
    public Response atualizar(@PathParam("id") Long id, Sala sala) {
        try {
            sala = salaService.atualizar(id, sala);
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
        summary = "Deletar sala",
        description = "Deleta apenas uma sala a partir do seu id"
    )
    public Response deletar(@PathParam("id") Long id) {
        try {
            salaService.deletar(id);
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
