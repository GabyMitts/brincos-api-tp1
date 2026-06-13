package br.unitins.topicos1.brincos.resource;


import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.topicos1.brincos.dto.PedidoDTO;
import br.unitins.topicos1.brincos.dto.PedidoDTOResponse;
import br.unitins.topicos1.brincos.service.PedidoService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoService service;

    @Inject
    JsonWebToken jwt;

    @GET
    @RolesAllowed({"Admin"})
    public Response buscarTodos() {
        return Response.ok(service.getAll()).build();
    }
    
    @POST
    @Authenticated
    public Response incluir(PedidoDTO dto) {
        String email = jwt.getSubject();
        if (email == null) {
            email = jwt.getClaim("email");
        }
        PedidoDTOResponse retorno = service.create(dto, email);

        return Response.status(201).entity(retorno).build();
    }

    @GET
    @Authenticated
    @Path("/meus-pedidos")
    public Response buscarPorUsuario() {
        String email = jwt.getSubject();
        // Fallback: se subject estiver null, tenta pegar do claim email
        if (email == null) {
            email = jwt.getClaim("email");
        }
        return Response.ok(service.findByUsuario(email)).build();
    }

    @GET
    @Authenticated
    @Path("/cupom")
    public Response buscarPorCupomDesconto(@QueryParam("codigo") String codigo,
                                           @QueryParam("valorTotal") Float valorTotal) {
        String email = jwt.getSubject();
        // Fallback: se subject estiver null, tenta pegar do claim email
        if (email == null) {
            email = jwt.getClaim("email");
        }
        return Response.ok(service.findByCupomDesconto(codigo, email, valorTotal)).build();
    }
}