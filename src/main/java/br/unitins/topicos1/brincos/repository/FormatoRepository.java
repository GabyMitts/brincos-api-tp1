package br.unitins.topicos1.brincos.repository;

import java.util.List;

import br.unitins.topicos1.brincos.model.Formato;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FormatoRepository implements PanacheRepository<Formato> {
    
    public List<Formato> findByNome(String nome){
        return find("SELECT f FROM Formato f WHERE f.nome LIKE ?1 ", "%"+nome+"%").list();
    }
}
