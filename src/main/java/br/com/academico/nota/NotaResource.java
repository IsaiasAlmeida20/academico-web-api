package br.com.academico.nota;

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

@Path("/notas")
@Tag(name = "Nota")
public class NotaResource {

    private INotaService notaService;
    
    @Inject
    
    public NotaResource(@Named("notaservicedefault") INotaService notaService) {
        this.notaService = notaService;
    } 

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Listar Notas",
        description = "Recupera uma lista completa de Notas com todos os dados"
    )
    public Response recuperar() {
        List<Nota> listNotas = new ArrayList<Nota>();
        try {
            listNotas = notaService.listar();
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }
        return Response.ok(listNotas, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Recuperar Nota",
        description = "Recupera apenas uma Nota a partir do seu id"
    )
    public Response recuperarId(@PathParam("id") Long id) {
        Nota nota;
        try {
            nota = notaService.recuperar(id);
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }
        return Response.ok(nota, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Criar Nota",
        description = "Cria um Nota"
    )
    public Response inserir(Nota nota) {
        Long id;
        try {
            id = notaService.criar(nota);
            nota.setId(id);
        } catch (Exception e) {
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .type("text/plain")
                .build();
        }
        return Response
                    .status(Response.Status.CREATED)
                    .entity(nota)
                    .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Atualiza um nota",
        description = "Atualiza um nota"
    )
    public Response atualizar(@PathParam("id") Long id, Nota nota) {
        try {
            nota = notaService.atualizar(id, nota);
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
        summary = "Deletar Nota",
        description = "Deleta apenas uma nota a partir do seu id"
    )
    public Response deletar(@PathParam("id") Long id) {
        try {
            notaService.deletar(id);
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
