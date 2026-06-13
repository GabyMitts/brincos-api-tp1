package br.unitins.topicos1.brincos.repository;

import java.util.List;

import br.unitins.topicos1.brincos.model.Cor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CorRepository implements PanacheRepository<Cor> {
    
    public List<Cor> findByNome(String nome){
        return find("SELECT c FROM Cor c WHERE c.nome LIKE ?1 ", "%"+nome+"%").list();
    }
}
