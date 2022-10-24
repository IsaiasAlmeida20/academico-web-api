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

@Path("/salas")
public class SalaResource {
    
    private Sala sala;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperar() {
        List<Sala> listSalas = new ArrayList<Sala>();
        listSalas.add(new Sala(1, 30, true, true, false));
        listSalas.add(new Sala(2, 30, true, true, true));
        return Response.ok(listSalas, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperarId(@PathParam("id") int id) {
        sala = new Sala(3, 30, true, true, false);
        sala.setId(id);
        return Response.ok(sala, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir() {
        return Response.status(201, MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") int id) {
        return Response.status(202, MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        return Response.status(201, MediaType.APPLICATION_JSON).build();
    }
}
