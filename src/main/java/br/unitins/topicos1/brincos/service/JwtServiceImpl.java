package br.unitins.topicos1.brincos.service;


import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import br.unitins.topicos1.brincos.model.Perfil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtServiceImpl implements JwtService {

    private static final Duration EXPIRATION_TIME = Duration.ofHours(24);

    @Override
    public String generateJwt(String email, Perfil perfil) {

        // data
        Instant expiryDate = Instant.now().plus(EXPIRATION_TIME);

        // papeis (perfil)
        Set<String> roles = new HashSet<String>();
        roles.add(perfil.name());

        // gerando o token
        return Jwt.issuer("unitins-jwt")
                .subject(email)
                .claim("perfil", perfil.name())
                .groups(roles)
                .expiresAt(expiryDate)
                .sign();

    }
    
}