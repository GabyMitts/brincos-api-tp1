package br.unitins.topicos1.brincos.repository;

import java.util.List;

import br.unitins.topicos1.brincos.model.CupomDesconto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CupomDescontoRepository implements PanacheRepository<CupomDesconto> {
    
    public List<CupomDesconto> findByNome(String nome){
        return find("SELECT c FROM CupomDesconto c WHERE c.nome LIKE ?1 ", "%"+nome+"%").list();
    }

    public CupomDesconto findByCodigo(String codigo){
        return find("SELECT c FROM CupomDesconto c WHERE c.codigo = ?1", codigo).firstResult();
    }
}
