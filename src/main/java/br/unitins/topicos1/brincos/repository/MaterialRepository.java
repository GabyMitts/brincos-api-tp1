package br.unitins.topicos1.brincos.repository;

import java.util.List;

import br.unitins.topicos1.brincos.model.Material;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MaterialRepository implements PanacheRepository<Material> {
    
    public List<Material> findByNome(String nome){
        return find("SELECT m FROM Material m WHERE m.nome LIKE ?1 ", "%"+nome+"%").list();
    }
}
