package br.com.academico.aluno;

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


@Path("/alunos")
@Tag(name = "Aluno")
public class AlunoResource {

    @Inject
    @Named("alunoservicedefaut")
    private IAlunoService alunoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Listar Alunos",
        description = "Recupera uma lista completa de alunos com todos os dados"
    )
    public Response recuperar() {
        List<Aluno> listAlunos = new ArrayList<Aluno>();
        try {
            listAlunos = alunoService.listar();
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }
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
        Aluno aluno;
        try {
            aluno = alunoService.recuperar(matricula);
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }
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
        int matricula;
        try {
            matricula = alunoService.criar(aluno);
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }
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
        try {
            aluno =alunoService.atualizar(matricula, aluno);
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
        summary = "Deletar aluno",
        description = "Deleta apenas um aluno a partir do sua matricula"
    )
    public Response deletar(@PathParam("matricula") int matricula) {
        try {
            alunoService.deletar(matricula);
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


    @GET
    @Produces
    @Path("{matricula}/notas")
    @Operation(
        summary = "Lista Notas",
        description = "Recupera uma lista com todas as notas do aluno"
    )
    public Response recuperarNotasPorMatricula(@PathParam("matricula") int matricula) {
        List<Nota> notas = new ArrayList<Nota>();
        try {
            alunoService.listarNotas(matricula);
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }

        return Response.ok(notas).build();
    }
}
