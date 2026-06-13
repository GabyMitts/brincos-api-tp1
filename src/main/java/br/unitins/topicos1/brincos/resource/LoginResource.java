package br.unitins.topicos1.brincos.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.topicos1.brincos.dto.LoginDTO;
import br.unitins.topicos1.brincos.dto.LoginDTOResponse;
import br.unitins.topicos1.brincos.dto.UsuarioDTOResponse;
import br.unitins.topicos1.brincos.service.LoginService;
import br.unitins.topicos1.brincos.service.UsuarioService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {

    @Inject
    LoginService loginService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    JsonWebToken jwt;
     
    @POST
    @Path("/login")
    public LoginDTOResponse login(LoginDTO loginDTO) {
        return loginService.login(loginDTO);
    }

     @GET
    @Path("/me")
    @Authenticated
    public Response me() {
        UsuarioDTOResponse usuario = usuarioService.findByEmail(jwt.getSubject())
                .map(UsuarioDTOResponse::valueOf)
                .orElse(null);

        if (usuario == null) {
            throw new jakarta.ws.rs.WebApplicationException("Usuario nao encontrado", Status.UNAUTHORIZED);
        }
        return Response.ok(usuario).build();
    }

}