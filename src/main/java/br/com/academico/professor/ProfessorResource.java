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

@Path("/professores")
public class ProfessorResource {

    private Professor professor;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
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
    public Response recuperarMatricula(@PathParam("matricula") int matricula) {
        professor = new Professor("Jose", "Silva", 34, "BA", 'M', "777.777.777-77", 5.000, 40);
        professor.setMatricula(matricula);
        return Response.ok(professor, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
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
    public Response atualizar(@PathParam("matricula") int matricula, Professor professor) {
        professor.setMatricula(20221998);
        return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
    }

    @DELETE
    @Path("/{matricula}")
    public Response deletar(@PathParam("matricula") int matricula) {
        return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
    }
}
