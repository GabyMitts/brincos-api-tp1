package br.unitins.topicos1.brincos.repository;

import br.unitins.topicos1.brincos.model.ListaDesejo;
import br.unitins.topicos1.brincos.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ListaDesejoRepository implements PanacheRepository<ListaDesejo> {

    public PanacheQuery<ListaDesejo> findByUsuario(Usuario usuario) {
        if (usuario == null || usuario.getId() == null)
            return null;
        return find("usuario.id = ?1 ",  usuario.getId());
    }

}