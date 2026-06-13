package br.unitins.topicos1.brincos.resource;

import java.util.List;

import br.unitins.topicos1.brincos.dto.PiercingDTO;
import br.unitins.topicos1.brincos.dto.PiercingDTOResponse;
import br.unitins.topicos1.brincos.service.PiercingService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/piercings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PiercingResource {

    @Inject
    PiercingService service;

    @GET
    public List<PiercingDTOResponse> buscarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/find/{cor}")
    public List<PiercingDTOResponse> buscarPorCor(String cor) {
        return service.findByCor(cor);
    }

    @POST
    @Transactional
    @RolesAllowed("Admin")
    public PiercingDTOResponse incluir(PiercingDTO dto){
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed("Admin")
    public void alterar(Long id, PiercingDTO dto){
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed("Admin")
    public void apagar(Long id){
        service.delete(id);
    }

}

