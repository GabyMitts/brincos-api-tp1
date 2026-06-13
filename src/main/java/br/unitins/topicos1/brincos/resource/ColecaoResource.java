package br.unitins.topicos1.brincos.resource;

import java.util.List;

import br.unitins.topicos1.brincos.dto.ColecaoDTO;
import br.unitins.topicos1.brincos.dto.ColecaoDTOResponse;
import br.unitins.topicos1.brincos.service.ColecaoService;
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

@Path("/colecao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ColecaoResource {

    @Inject
    ColecaoService service;

    @GET
    public List<ColecaoDTOResponse> buscarTodos(@DefaultValue("0") @QueryParam("page") int page,
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
    @Path("/find/{nome}")
    public List<ColecaoDTOResponse> buscarPorNome(String nome) {
        return service.findByNome(nome);
    }

    @GET
    @Path("/{id}")
    public ColecaoDTOResponse buscarPorId(Long id) {
        return service.findById(id);
    }

    @POST
    @Transactional
    @RolesAllowed("Admin")
    public ColecaoDTOResponse incluir(ColecaoDTO dto) {
        return service.create(dto);
    }

    //@RolesAllowed("User")
    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed("Admin")
    public void alterar(Long id, ColecaoDTO dto) {
        service.update(id, dto);
    }

    //@RolesAllowed("User")
    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed("Admin")
    public void apagar(Long id) {
        service.delete(id);
    }
}