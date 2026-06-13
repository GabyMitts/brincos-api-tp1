package br.unitins.topicos1.brincos.util;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;

import br.unitins.topicos1.brincos.model.Usuario;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthTokenService {

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    String issuer;

    public String generateTokenJWT(Usuario usuario, Instant expireTime) {

        Set<String> roles = new HashSet<String>();
        roles.add(usuario.getPerfil().name());
        
        if (expireTime == null) {
            expireTime = Instant.now().plus(Duration.ofHours(24));
        }

        String token =
                Jwt.issuer(issuer)
                        .expiresAt(expireTime)
                        .subject(usuario.getEmail())
                        .claim("perfil", usuario.getPerfil().name())
                        .claim("expireTime", expireTime)
                        .claim("Nome", usuario.getNome())
                        .groups(roles)
                        .claim(Claims.email, usuario.getEmail())
                        .sign();
        System.out.println(token);

        return token;
    }
}