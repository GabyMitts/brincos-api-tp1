package br.unitins.topicos1.brincos.resource;

import java.util.List;

import br.unitins.topicos1.brincos.dto.CorDTO;
import br.unitins.topicos1.brincos.dto.CorDTOResponse;
import br.unitins.topicos1.brincos.service.CorService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/cor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CorResource {

    private static final int MAX_PAGE_SIZE = 9999999;

    @Inject
    CorService service;

    @GET
    public List<CorDTOResponse> buscarTodos(
        @QueryParam("page")     @DefaultValue("0")   int page,
        @QueryParam("pageSize") @DefaultValue("9999999") int pageSize
    ) {
        page = Math.max(0, page);
        pageSize = Math.min(Math.max(1, pageSize), MAX_PAGE_SIZE);
        return service.findAll(page, pageSize);
    }

    @GET
    @Path("/count")
    public long total() {
        return service.count();
    }

    @GET
    @Path("/{id}")
    public CorDTOResponse buscarPorId(Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/find/{nome}")
    public List<CorDTOResponse> buscarPorNome(String nome) {
        return service.findByNome(nome);
    }

    @POST
    @RolesAllowed("Admin")
    public CorDTOResponse incluir(CorDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("Admin")
    public void alterar(Long id, CorDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("Admin")
    public void apagar(Long id) {
        service.delete(id);
    }
}