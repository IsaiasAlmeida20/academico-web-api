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

@Path("/enderecos")
public class EnderecoResource {

    private Endereco endereco;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperar() {
        List<Endereco> listEnderecos = new ArrayList<Endereco>();
        listEnderecos.add(new Endereco(49300000, "Rua a", "Macaé", "Tobias Barreto", "SE"));
        listEnderecos.add(new Endereco(49400000, "Rua b", "Centro", "Lagarto", "SE"));
        return Response.ok(listEnderecos, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response recuperarId(@PathParam("id") int id) {
        endereco = new Endereco(49300000, "Rua a", "Macaé", "Tobias Barreto", "SE");
        endereco.setId(id);
        return Response.ok(endereco, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(Endereco endereco) {
        endereco.setId(10);
        return Response
                    .status(Response.Status.CREATED)
                    .entity(endereco)
                    .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(Endereco endereco) {
        return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        return Response 
                    .status(Response.Status.NO_CONTENT)
                    .build();
    }

    @PUT
    @Path("{id}/{status}")
    @Consumes(MediaType.TEXT_PLAIN)
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
