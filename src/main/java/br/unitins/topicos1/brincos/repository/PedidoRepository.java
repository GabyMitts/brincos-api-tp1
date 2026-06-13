package br.unitins.topicos1.brincos.repository;

import br.unitins.topicos1.brincos.model.CupomDesconto;
import br.unitins.topicos1.brincos.model.Pedido;
import br.unitins.topicos1.brincos.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

    public PanacheQuery<Pedido> findByUsuario(Usuario usuario) {
        if (usuario == null || usuario.getId() == null)
            return null;
        return find("usuario.id = ?1 ",  usuario.getId());
    }

    public PanacheQuery<Pedido> findByCupomDesconto(CupomDesconto cupomDesconto, Usuario usuario) {
        if (cupomDesconto == null || cupomDesconto.getId() == null)
            return null;
        return find("cupomDesconto.id = ?1 and usuario.id = ?2",  cupomDesconto.getId(), usuario.getId());
    }

}