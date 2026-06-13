package br.unitins.topicos1.brincos.repository;

import java.util.List;

import br.unitins.topicos1.brincos.model.Piercing;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PiercingRepository implements PanacheRepository<Piercing> {
    
    public List<Piercing> findByCor(String cor){
        return find("SELECT b FROM Piercing b WHERE b.cor LIKE ?1", "%"+cor+"%").list();
    }
}
