package br.unitins.topicos1.brincos.repository;

import br.unitins.topicos1.brincos.model.ItemListaDesejo;
import br.unitins.topicos1.brincos.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItensListaDesejoRepository implements PanacheRepository<ItemListaDesejo> {
    public PanacheQuery<ItemListaDesejo> findByLista(Long idLista, Usuario usuario) {
        if (usuario == null || usuario.getId() == null)
            return null;
        return find("listaDesejo.usuario.id = ?1 and listaDesejo.id = ?2", usuario.getId(), idLista);
    }
}