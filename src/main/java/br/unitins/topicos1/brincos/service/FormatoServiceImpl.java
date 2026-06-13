package br.unitins.topicos1.brincos.service;

import java.util.List;

import br.unitins.topicos1.brincos.dto.FormatoDTO;
import br.unitins.topicos1.brincos.dto.FormatoDTOResponse;
import br.unitins.topicos1.brincos.model.Formato;
import br.unitins.topicos1.brincos.repository.FormatoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FormatoServiceImpl implements FormatoService {
    
    @Inject
    FormatoRepository repository;

    @Override
    public List<FormatoDTOResponse> findAll(int page, int pageSize){
        return repository
                    .listAll()
                    .stream()
                    .skip(page * pageSize)
                    .limit(pageSize)
                    .map(F -> FormatoDTOResponse.valueOf(F))
                    .toList();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<FormatoDTOResponse> findByNome(String nome){
        return repository
                    .findByNome(nome)
                    .stream()
                    .map(F -> FormatoDTOResponse.valueOf(F))
                    .toList();
    }

    @Override
    public FormatoDTOResponse findById(Long id){
        Formato formato = repository.findById(id);
        if (formato == null) {
            return null;    
        }
        return FormatoDTOResponse.valueOf(formato);
    }

    @Override
    @Transactional
    public FormatoDTOResponse create(FormatoDTO dto){

        Formato formato = new Formato();
        formato.setNome(dto.nome());
        repository.persist(formato);
        return FormatoDTOResponse.valueOf(formato);
    }

    @Override
    @Transactional
    public void update(Long id, FormatoDTO dto){
        
        Formato formato = repository.findById(id);
        if (formato == null) {
            return;
        }
        formato.setNome(dto.nome());
    }

    @Override
    @Transactional
    public void delete(Long id){

        repository.deleteById(id);

    }
}
