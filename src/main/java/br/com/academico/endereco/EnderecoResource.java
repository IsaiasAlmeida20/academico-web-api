package br.com.academico.endereco;

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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/enderecos")
@Tag(name = "Endereço")
public class EnderecoResource {

    private Endereco endereco;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Listar Endereços",
        description = "Recupera uma lista completa de endereço com todos os dados"
    )
    public Response recuperar() {
        List<Endereco> listEnderecos = new ArrayList<Endereco>();
        listEnderecos.add(new Endereco(49300000, "Rua a", "Macaé", "Tobias Barreto", "SE"));
        listEnderecos.add(new Endereco(49400000, "Rua b", "Centro", "Lagarto", "SE"));
        return Response.ok(listEnderecos, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Recuperar Endereço",
        description = "Recupera apenas um endereço a partir do seu id"
    )
    public Response recuperarId(@PathParam("id") int id) {
        endereco = new Endereco(49300000, "Rua a", "Macaé", "Tobias Barreto", "SE");
        endereco.setId(id);
        return Response.ok(endereco, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Criar Endereço",
        description = "Cria um endereço completo"
    )
    public Response inserir(Endereco endereco) {
        endereco.setId(10);
        return Response
                    .status(Response.Status.CREATED)
                    .entity(endereco)
                    .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Atualiza um endereço",
        description = "Atualiza um endereço"
    )
    public Response atualizar(@PathParam("id") int id, Endereco endereco) {
        endereco.setId(id);
        return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(
        summary = "Deletar endereco",
        description = "Deleta apenas um endereço a partir do seu id"
    )
    public Response deletar(@PathParam("id") int id) {
        return Response 
                    .status(Response.Status.NO_CONTENT)
                    .build();
    }

    @PUT
    @Path("{id}/{status}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Operation(
        summary = "Atualiza Status do endereço",
        description = "Ativaou destaiva o status do endereço",
        requestBody = @RequestBody(
            description = "Status do endereço",
            required = true,
            content = @Content(
                schema = @Schema(implementation = EnderecoEnum.class)
            )
        )
    )
    public Response atualizarStatus(@PathParam("id") int id, String status) {
        endereco = new Endereco();
        endereco.setId(id);
        endereco.setStatus(EnderecoEnum.fromString(status));
        return Response
                    .status(Response.Status.OK)
                    .entity(endereco)
                    .build();
    }
    
}
