package br.unitins.topicos1.brincos.service;

import java.util.List;

import br.unitins.topicos1.brincos.dto.CorDTO;
import br.unitins.topicos1.brincos.dto.CorDTOResponse;
import br.unitins.topicos1.brincos.model.Cor;
import br.unitins.topicos1.brincos.repository.CorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CorServiceImpl implements CorService {
    
    @Inject
    CorRepository repository;

    @Override
    public List<CorDTOResponse> findAll(int page, int pageSize){
        return repository
                    .listAll()
                    .stream()
                    .skip(page * pageSize)
                    .limit(pageSize)
                    .map(C -> CorDTOResponse.valueOf(C))
                    .toList();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<CorDTOResponse> findByNome(String nome){
        return repository
                    .findByNome(nome)
                    .stream()
                    .map(C -> CorDTOResponse.valueOf(C))
                    .toList();
    }

    @Override
    public CorDTOResponse findById(Long id){
        var cor = repository.findById(id);
        if (cor == null) {
            return null;
        }
        return CorDTOResponse.valueOf(cor);
    }

    @Override
    @Transactional
    public CorDTOResponse create(CorDTO dto){

        Cor cor = new Cor();
        cor.setNome(dto.nome());
        repository.persist(cor);
        return CorDTOResponse.valueOf(cor);
    }

    @Override
    @Transactional
    public void update(Long id, CorDTO dto){
        Cor cor = repository.findById(id);
        if (cor == null) {
            return;
        }
        cor.setNome(dto.nome());
    }

    @Override
    @Transactional
    public void delete(Long id){

        repository.deleteById(id);

    }
}
