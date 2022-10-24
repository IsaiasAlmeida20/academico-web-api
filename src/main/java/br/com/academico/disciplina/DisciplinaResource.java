package br.com.academico.disciplina;

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

@Path("/disciplinas")
public class DisciplinaResource {
    
    private Disciplina disciplina;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperar() {
        List<Disciplina> listDisciplinas = new ArrayList<Disciplina>();
        listDisciplinas.add(new Disciplina("Cs1", 86));
        listDisciplinas.add(new Disciplina("Progarmação 1", 101));
        listDisciplinas.add(new Disciplina("E.S", 90));
        return Response.ok(listDisciplinas, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperarId(@PathParam("id") int id) {
        disciplina = new Disciplina("Progarmação 1", 101);
        disciplina.setId(id);
        return Response.ok(disciplina, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(Disciplina disciplina) {
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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletar(@PathParam("id") int id) {
        return Response.status(204, MediaType.APPLICATION_JSON).build();
    }
}
