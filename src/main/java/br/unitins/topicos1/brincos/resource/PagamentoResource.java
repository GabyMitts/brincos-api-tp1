package br.unitins.topicos1.brincos.resource;


import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.topicos1.brincos.dto.PagamentoDTO;
import br.unitins.topicos1.brincos.dto.PagamentoDTOResponse;
import br.unitins.topicos1.brincos.service.PagamentoService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pagamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PagamentoResource {

    @Inject
    PagamentoService service;

    @Inject
    JsonWebToken jwt;

    @GET
    @RolesAllowed({"Admin"})
    public Response buscarTodos() {
        return Response.ok(service.getAll()).build();
    }
    
    @POST
    @Authenticated
    public Response incluir(PagamentoDTO dto) {
        String email = jwt.getSubject();

        if (email == null) {
            email = jwt.getClaim("email");
        }

        PagamentoDTOResponse retorno = service.create(dto, email);

        return Response.status(201).entity(retorno).build();
    }

}