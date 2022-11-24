package br.com.academico.aluno;

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


@Path("/alunos")
@Tag(name = "Aluno")
public class AlunoResource {
    
    private Aluno aluno;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Listar Alunos",
        description = "Recupera uma lista completa de alunos com todos os dados"
    )
    public Response recuperar() {
        List<Aluno> listAlunos = new ArrayList<Aluno>();
        Aluno a1 = new Aluno("Isaias", "Almeida", 24, "SE", 'M', "999.999.999-99", "Informatica", true);
        Aluno a2 = new Aluno("Joãozinho", "petorto", 20, "SE", 'M', "999.999.999-88", "Informatica", true);
        a1.setEndereco(new Endereco(49300000, "Rua A", "Centro", "Tobias", "SE"));
        a2.setEndereco(new Endereco(49400000, "Rua B", "Centro", "Lagarto", "BA"));
        listAlunos.add(a2);
        listAlunos.add(a1);
        Nota n1 = new Nota(8, 1);
        Nota n2 = new Nota(10, 1);
        Nota n3 = new Nota(9, 1);
        List<Nota> notas = new ArrayList<Nota>();
        notas.add(n1);
        notas.add(n2);
        notas.add(n3);
        a1.setNotas(notas);
        a2.setNotas(notas);
        a1.calcularMediaAritimetica();
        a1.calcularMediaPonderada();
        a2.calcularMediaAritimetica();
        a2.calcularMediaPonderada();
        return Response.ok(listAlunos, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{matricula}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Recuperar Aluno",
        description = "Recupera apenas um aluno a partir do sua matricula"
    )
    public Response recuperarMatricula(@PathParam("matricula") int matricula) {
        aluno = new Aluno("Isaias", "Almeida", 24, "SE", 'M', "999.999.999-99", "Informatica", true);
        aluno.setMatricula(matricula);
        return Response.ok(aluno, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Criar Aluno",
        description = "Cria um aluno completo"
    )
    public Response inserir(Aluno aluno) {
        aluno.setMatricula(20221998);
        return Response
                    .status(Response.Status.CREATED)
                    .entity(aluno)
                    .build();
    }

    @PUT
    @Path("/{matricula}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Atualiza um aluno",
        description = "Atualiza um aluno"
    )
    public Response atualizar(@PathParam("matricula") int matricula, Aluno aluno) {
        aluno.setMatricula(20221998);
        return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
    }

    @DELETE
    @Path("/{matricula}")
    @Operation(
        summary = "Deletar aluno",
        description = "Deleta apenas um aluno a partir do sua matricula"
    )
    public Response deletar(@PathParam("matricula") int matricula) {
        return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
    }


    @GET
    @Produces
    @Path("{matricula}/notas")
    @Operation(
        summary = "Lista Notas",
        description = "Recupera uma lista com todas as notas do aluno"
    )
    public Response recuperarNotasPorMatricula(@PathParam("matricula") int matricula) {
        Nota n1 = new Nota(8, 1);
        Nota n2 = new Nota(10, 1);
        Nota n3 = new Nota(9, 1);
        List<Nota> notas = new ArrayList<Nota>();
        notas.add(n1);
        notas.add(n2);
        notas.add(n3);
        return Response.ok(notas).build();
    }
}
