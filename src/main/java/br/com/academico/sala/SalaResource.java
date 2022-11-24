package br.com.academico.sala;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/salas")
@Tag(name = "Sala")
public class SalaResource {
    
    private Sala sala;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Listar Salas",
        description = "Recupera uma lista completa de salas com todos os dados"
    )
    public Response recuperar() {
        List<Sala> listSalas = new ArrayList<Sala>();
        listSalas.add(new Sala(1, 30, true, true, false));
        listSalas.add(new Sala(2, 30, true, true, true));
        return Response.ok(listSalas, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Recuperar Sala",
        description = "Recupera apenas uma sala a partir do seu id"
    )
    public Response recuperarId(@PathParam("id") int id) {
        sala = new Sala(3, 30, true, true, false);
        sala.setId(id);
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
        sala.setId(2);
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
    public Response atualizar(@PathParam("id") int id, Sala sala) {
        sala.setId(id);
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
    public Response deletar(@PathParam("id") int id) {
        return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
    }
}
