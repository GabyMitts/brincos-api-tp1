package br.unitins.topicos1.brincos.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCrypt;

import br.unitins.topicos1.brincos.dto.LoginDTO;
import br.unitins.topicos1.brincos.dto.LoginDTOResponse;
import br.unitins.topicos1.brincos.model.Usuario;
import br.unitins.topicos1.brincos.repository.UsuarioRepository;
import br.unitins.topicos1.brincos.util.AuthTokenService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;

@ApplicationScoped
public class LoginService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    AuthTokenService authTokenService;

    public LoginDTOResponse login(LoginDTO loginDTO) {

        LoginDTOResponse loginDTOResponse = new LoginDTOResponse();
        
        Optional<Usuario> usuario = usuarioRepository.findByEmail(loginDTO.getEmail());

        if (usuario.isEmpty()) {
            throw new NotAuthorizedException("Erro ao efetuar a autenticação");
        }

        if (!BCrypt.checkpw(loginDTO.getSenha(), usuario.get().getSenha())) {
            throw new NotAuthorizedException("E-mail ou senha inválido.");
        }

        Instant expireTime = Instant.now().plus(Duration.ofHours(24));

        String token = authTokenService.generateTokenJWT(usuario.get(), expireTime);

        loginDTOResponse.setEmail(usuario.get().getEmail());
        if (usuario.get().getPerfil().name().equals("Admin"))
            loginDTOResponse.setPerfil("Admin");
        loginDTOResponse.setPerfil(usuario.get().getPerfil().name());
        loginDTOResponse.setNome(usuario.get().getNome());
        loginDTOResponse.setExpireTime(expireTime.toString());
        loginDTOResponse.setToken(token);

        return loginDTOResponse;
    }
}