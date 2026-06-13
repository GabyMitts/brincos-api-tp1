package br.unitins.topicos1.brincos.resource;

import java.util.List;

import br.unitins.topicos1.brincos.dto.LoginDTO;
import br.unitins.topicos1.brincos.dto.LoginDTOResponse;
import br.unitins.topicos1.brincos.dto.UsuarioDTO;
import br.unitins.topicos1.brincos.dto.UsuarioDTOEmail;
import br.unitins.topicos1.brincos.dto.UsuarioDTOPwRecuperar;
import br.unitins.topicos1.brincos.dto.UsuarioDTOPwReset;
import br.unitins.topicos1.brincos.dto.UsuarioDTOResponse;
import br.unitins.topicos1.brincos.model.Usuario;
import br.unitins.topicos1.brincos.service.LoginService;
import br.unitins.topicos1.brincos.service.UsuarioService;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
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

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService service;

    @Inject
    LoginService loginService;


    @Inject
    SecurityIdentity identity;

    @GET
    @Authenticated
    public List<UsuarioDTOResponse> buscarTodos() {
        return service.findAll();
    }

    @PUT
    @Path("/{id}")
    @Authenticated
    public UsuarioDTOResponse atualizar(@PathParam("id") Long id, UsuarioDTO dto) {

        Usuario usuario = service.findByEmail(identity.getPrincipal().getName())
        .orElseThrow(() -> new NotAuthorizedException("Usuario autenticado nao encontrado."));        

        LoginDTO login = new LoginDTO();
        login.email = usuario.getEmail();
        login.senha = dto.senha();
        LoginDTOResponse loginRESP = loginService.login(login);
        if (loginRESP == null || loginRESP.getToken() == null) {
            throw new NotAuthorizedException("Senha incorreta.");
        }
        
        UsuarioDTO usuarioDTO = new UsuarioDTO(
            dto.nome() != null ? dto.nome() : usuario.getNome(),
            dto.email() != null ? dto.email() : usuario.getEmail(),
            dto.senha(),
            dto.idPerfil() != null ? dto.idPerfil() : usuario.getPerfil().ID,
            dto.telefone() != null ? dto.telefone() : usuario.getTelefone(),
            ""
        );
        if (identity.hasRole("Admin")) {
            return service.update(id, usuarioDTO);
        }
        usuarioDTO = new UsuarioDTO(
            dto.nome() != null ? dto.nome() : usuario.getNome(),
            dto.email() != null ? dto.email() : usuario.getEmail(),
            dto.senha(),
            usuario.getPerfil().ID,
            dto.telefone() != null ? dto.telefone() : usuario.getTelefone(),
            ""
        );
        
        return service.update(usuario.getId(), usuarioDTO);
    }

    @POST
    public UsuarioDTOResponse incluir(UsuarioDTO dto) {
        return service.create(dto);
    }

    @POST
    @Path("/admin")
    @RolesAllowed("Admin")
    public UsuarioDTOResponse incluirAdmin(UsuarioDTO dto) {
        return service.createAdmin(dto);
    }

    @POST
    @Path("/getTokenRecuperacaoSenha")
    public Boolean getTokenRecuperacaoSenha(UsuarioDTOEmail email) {
        return service.getTokenRecuperacaoSenha(email);
    }

    @POST
    @Path("/recuperarSenha")
    public Boolean recuperarSenha(UsuarioDTOPwRecuperar dto) {
        return service.recuperarSenha(dto.email(), dto.novaSenha(), dto.token());
    }

    @POST
    @Path("/alterarSenha")
    public Boolean alterarSenha(UsuarioDTOPwReset dto) {
        String email = identity.getPrincipal().getName();
        return service.resetarSenha(email, dto.senha(), dto.novaSenha());
    }

}

