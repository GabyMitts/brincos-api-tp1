package br.unitins.topicos1.brincos.repository;

import java.util.Optional;

import br.unitins.topicos1.brincos.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public Usuario findByEmailSenha(String email, String senha) {
        return find("SELECT u FROM Usuario u WHERE u.email = ?1 AND u.senha = ?2 ", email, senha).firstResult();
    }

    public Optional<Usuario> findByEmail(String email) {
        return find("email = :email", Parameters.with("email", email)).firstResultOptional();
    }

}