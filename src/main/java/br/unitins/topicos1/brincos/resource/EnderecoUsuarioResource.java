package br.unitins.topicos1.brincos.resource;

import java.util.List;

import br.unitins.topicos1.brincos.dto.EnderecoUsuarioDTO;
import br.unitins.topicos1.brincos.dto.EnderecoUsuarioDTOResponse;
import br.unitins.topicos1.brincos.service.EnderecoUsuarioService;
import br.unitins.topicos1.brincos.service.UsuarioService;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/usuario/endereco")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoUsuarioResource {

    @Inject
    EnderecoUsuarioService service;

    @Inject
    UsuarioService usuarioService;

    @Inject
    SecurityIdentity identity;

    @GET
    @Authenticated
    @Path("/list/{id}")
    public List<EnderecoUsuarioDTOResponse> buscarTodos(@PathParam("id") Long id) {
        if (identity.hasRole("Admin")) {
            return service.findAll(id);
        }

        Long usuarioId = usuarioService.findByEmail(identity.getPrincipal().getName())
                .map(usuario -> usuario.getId())
                .orElseThrow(() -> new NotAuthorizedException("Usuario autenticado nao encontrado."));

        return service.findAll(usuarioId);
    }

    @GET
    @Authenticated
    @Path("/{id}")
    public EnderecoUsuarioDTOResponse findById(@PathParam("id") Long id) {
        if (identity.hasRole("Admin")) {
            return service.findById(id);
        }
        Long usuarioId = usuarioService.findByEmail(identity.getPrincipal().getName())
                .map(usuario -> usuario.getId())
                .orElseThrow(() -> new NotAuthorizedException("Usuario autenticado nao encontrado."));
        return service.findById(usuarioId);
    }

    @POST
    @Path("/")
    @Authenticated
    public EnderecoUsuarioDTOResponse incluir(EnderecoUsuarioDTO dto) {
        if (identity.hasRole("Admin")) {
            return service.create(dto);
        }
        Long usuarioId = usuarioService.findByEmail(identity.getPrincipal().getName())
                .map(usuario -> usuario.getId())
                .orElseThrow(() -> new NotAuthorizedException("Usuario autenticado nao encontrado."));
        EnderecoUsuarioDTO dto2 = new EnderecoUsuarioDTO(
            usuarioId,
            dto.rua(),
            dto.numero(),
            dto.complemento(),
            dto.bairro(),
            dto.cidade(),
            dto.estado(),
            dto.pais(),
            dto.cep()
        );
        return service.create(dto2);
    }

    @PUT
    @Path("/{id}")
    @Authenticated
    public EnderecoUsuarioDTOResponse atualizar(@PathParam("id") Long id, EnderecoUsuarioDTO dto) {
        if (identity.hasRole("Admin")) {
            return service.update(dto);
        }
        Long usuarioId = usuarioService.findByEmail(identity.getPrincipal().getName())
                .map(usuario -> usuario.getId())
                .orElseThrow(() -> new NotAuthorizedException("Usuario autenticado nao encontrado."));
        
        EnderecoUsuarioDTOResponse enderecoUsuario = service.findById(id);
        if (enderecoUsuario == null || !enderecoUsuario.usuario().id().equals(usuarioId)) {
            throw new NotAuthorizedException("EnderecoUsuario nao encontrado ou nao pertence ao usuario autenticado.");
        }

        EnderecoUsuarioDTO dto2 = new EnderecoUsuarioDTO(
            usuarioId,
            dto.rua(),
            dto.numero(),
            dto.complemento(),
            dto.bairro(),
            dto.cidade(),
            dto.estado(),
            dto.pais(),
            dto.cep()
        );
        return service.update(dto2);
    }



}

