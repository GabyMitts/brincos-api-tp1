package br.unitins.topicos1.brincos.repository;


import br.unitins.topicos1.brincos.model.EnderecoUsuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoUsuarioRepository implements PanacheRepository<EnderecoUsuario> {

}