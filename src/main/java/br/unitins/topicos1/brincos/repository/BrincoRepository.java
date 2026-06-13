package br.unitins.topicos1.brincos.repository;

import java.util.List;
import java.util.Optional;

import br.unitins.topicos1.brincos.model.Brinco;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BrincoRepository implements PanacheRepository<Brinco> {
    
    public List<Brinco> findByCor(String cor){
        return find("SELECT b FROM Brinco b JOIN b.cor c WHERE c.nome LIKE ?1", "%"+cor+"%").list();
    }

    public Optional<Brinco> findByArquivoId(Long arquivoId) {
        return find("SELECT b FROM Brinco b JOIN b.arquivos a WHERE a.id = ?1", arquivoId).firstResultOptional();
    }

}
