package br.unitins.topicos1.brincos.resource;

import java.util.List;

import br.unitins.topicos1.brincos.dto.FormatoDTO;
import br.unitins.topicos1.brincos.dto.FormatoDTOResponse;
import br.unitins.topicos1.brincos.service.FormatoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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

@Path("/formato")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FormatoResource {

    @Inject
    FormatoService service;

    @GET
    public List<FormatoDTOResponse> buscarTodos(@DefaultValue("0") @QueryParam("page") int page,
                                                 @DefaultValue("9999999") @QueryParam("pageSize") int pageSize) {
        page = Math.max(0, page);
        pageSize = Math.min(Math.max(1, pageSize), 9999999);
        return service.findAll(page, pageSize);
    }

    @GET
    @Path("/count")
    public long contar() {
        return service.count();
    }

    @GET
    @Path("/{id}")
    public FormatoDTOResponse buscarPorId(Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/find/{nome}")
    public List<FormatoDTOResponse> buscarPorNome(String nome) {
        return service.findByNome(nome);
    }

    @POST
    @Transactional
    @RolesAllowed("Admin")
    public FormatoDTOResponse incluir(FormatoDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("Admin")
    public void alterar(Long id, FormatoDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("Admin")
    public void apagar(Long id) {
        service.delete(id);
    }
}