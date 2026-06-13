package br.unitins.topicos1.brincos.resource;


import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.topicos1.brincos.dto.ListaDesejoDTO;
import br.unitins.topicos1.brincos.dto.ListaDesejoDTOResponse;
import br.unitins.topicos1.brincos.service.ListaDesejoService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/listadesejo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ListaDesejoResource {

    @Inject
    ListaDesejoService service;

    @Inject
    JsonWebToken jwt;

    @GET
    @Authenticated
    public Response buscarTodos() {
        return Response.ok(service.getAll()).build();
    }
    
    @POST
    @Authenticated
    public Response incluir(ListaDesejoDTO dto) {
        String email = jwt.getSubject();
        if (email == null) {
            email = jwt.getClaim("email");
        }
        ListaDesejoDTOResponse retorno = service.create(dto, email);

        return Response.status(201).entity(retorno).build();
    }

    @GET
    @Authenticated
    @Path("/minhas-listas")
    public Response buscarPorUsuario() {
        String email = jwt.getSubject();
        if (email == null) {
            email = jwt.getClaim("email");
        }
        return Response.ok(service.findByUsuario(email)).build();
    }

    @DELETE
    @Authenticated
    @Path("/{id}")
    public Response deletar(Long id) {
        service.delete(id);
        return Response.noContent().build();
    }

}