package br.unitins.topicos1.brincos.resource;

import java.util.List;

import br.unitins.topicos1.brincos.dto.CupomDescontoDTO;
import br.unitins.topicos1.brincos.dto.CupomDescontoDTOResponse;
import br.unitins.topicos1.brincos.service.CupomDescontoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/cupomdesconto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CupomDescontoResource {

    @Inject
    CupomDescontoService service;

    @GET
    @RolesAllowed("Admin")
    public List<CupomDescontoDTOResponse> buscarTodos(@DefaultValue("0") @QueryParam("page") int page,
                                                      @DefaultValue("9999999") @QueryParam("pageSize") int pageSize) {
        page = Math.max(0, page);
        pageSize = Math.min(Math.max(1, pageSize), 9999999);
        return service.findAll(page, pageSize);
    }

    @GET
    @Path("/count")
    @RolesAllowed("Admin")
    public long contar() {
        return service.count();
    }  

    @GET
    @Path("/{id}")
    @RolesAllowed("Admin")
    public CupomDescontoDTOResponse buscarPorId(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/find/{nome}")
    @RolesAllowed("Admin")
    public List<CupomDescontoDTOResponse> buscarPorNome(@PathParam("nome") String nome) {
        return service.findByNome(nome);
    }

    @POST
    @RolesAllowed("Admin")
    public CupomDescontoDTOResponse incluir(CupomDescontoDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("Admin")
    public void alterar(@PathParam("id") Long id, CupomDescontoDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("Admin")
    public void apagar(@PathParam("id") Long id) {
        service.delete(id);
    }
}