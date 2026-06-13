package br.unitins.topicos1.brincos.resource;


import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.topicos1.brincos.dto.ItemListaDesejoDTO;
import br.unitins.topicos1.brincos.dto.ItemListaDesejoDTOResponse;
import br.unitins.topicos1.brincos.service.ItemListaDesejoService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/item-listas-desejo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemListaDesejoResource {

    @Inject
    ItemListaDesejoService service;

    @Inject
    JsonWebToken jwt;

    @GET
    @Authenticated
    @Path("/{idLista}")
    public Response buscarTodos(@PathParam("idLista") Long idLista) {
        String email = jwt.getSubject();
        if (email == null) {
            email = jwt.getClaim("email");
        }
        return Response.ok(service.getAll(idLista, email)).build();
    }
    
    @POST
    @Authenticated
    @Path("/{idLista}")
    public Response incluir(@PathParam("idLista") Long idLista, ItemListaDesejoDTO dto) {
        String email = jwt.getSubject();
        if (email == null) {
            email = jwt.getClaim("email");
        }
        ItemListaDesejoDTOResponse retorno = service.create(dto, idLista, email);

        return Response.status(201).entity(retorno).build();
    }

    @DELETE
    @Authenticated
    @Path("/{idLista}/{idItem}")
    public Response excluir(@PathParam("idLista") Long idLista, @PathParam("idItem") Long idItem) {
        String email = jwt.getSubject();
        if (email == null) {
            email = jwt.getClaim("email");
        }
        service.delete(idItem, email);
        return Response.noContent().build();
    }

}
