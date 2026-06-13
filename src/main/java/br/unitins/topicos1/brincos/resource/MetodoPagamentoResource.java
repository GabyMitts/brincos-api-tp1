package br.unitins.topicos1.brincos.resource;

import br.unitins.topicos1.brincos.service.MetodoPagamentoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/metodo-pagamento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MetodoPagamentoResource {

    @Inject
    MetodoPagamentoService service;

    @GET
    public Response buscarTodos() {
        return Response.ok(service.getAll()).build();
    }
    

}