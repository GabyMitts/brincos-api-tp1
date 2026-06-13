package br.unitins.topicos1.brincos.repository;

import java.util.List;

import br.unitins.topicos1.brincos.model.Colecao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ColecaoRepository implements PanacheRepository<Colecao> {
    
    public List<Colecao> findByNome(String nome){
        return find("SELECT cc FROM Colecao cc WHERE cc.nome LIKE ?1 ", "%"+nome+"%").list();
    }
}
