package br.unitins.topicos1.brincos.service;

import java.util.List;

import br.unitins.topicos1.brincos.dto.MaterialDTO;
import br.unitins.topicos1.brincos.dto.MaterialDTOResponse;
import br.unitins.topicos1.brincos.model.Material;
import br.unitins.topicos1.brincos.repository.MaterialRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MaterialServiceImpl implements MaterialService {
    
    @Inject
    MaterialRepository repository;

    @Override
    public List<MaterialDTOResponse> findAll(int page, int pageSize){
        return repository
                    .listAll()
                    .stream()
                    .skip(page * pageSize)
                    .limit(pageSize)
                    .map(M -> MaterialDTOResponse.valueOf(M))
                    .toList();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<MaterialDTOResponse> findByNome(String nome){
        return repository
                    .findByNome(nome)
                    .stream()
                    .map(M -> MaterialDTOResponse.valueOf(M))
                    .toList();
    }

    @Override
    public MaterialDTOResponse findById(Long id){
        Material material = repository.findById(id);
        if (material == null) {
            return null;    
        }
        return MaterialDTOResponse.valueOf(material);
    }

    @Override
    @Transactional
    public MaterialDTOResponse create(MaterialDTO dto){

        Material material = new Material();
        material.setNome(dto.nome());
        repository.persist(material);
        return MaterialDTOResponse.valueOf(material);
    }

    @Override
    @Transactional
    public void update(Long id, MaterialDTO dto){
        
        Material material = repository.findById(id);
        if (material == null) {
            return;
        }
        material.setNome(dto.nome());
    }

    @Override
    @Transactional
    public void delete(Long id){

        repository.deleteById(id);

    }
}
