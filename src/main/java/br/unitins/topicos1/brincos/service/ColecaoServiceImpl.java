package br.unitins.topicos1.brincos.service;

import java.util.List;

import br.unitins.topicos1.brincos.dto.ColecaoDTO;
import br.unitins.topicos1.brincos.dto.ColecaoDTOResponse;
import br.unitins.topicos1.brincos.model.Colecao;
import br.unitins.topicos1.brincos.repository.ColecaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ColecaoServiceImpl implements ColecaoService {
    
    @Inject
    ColecaoRepository repository;

    @Override
    public List<ColecaoDTOResponse> findAll(int page, int pageSize){
        return repository
                    .listAll()
                    .stream()
                    .skip(page * pageSize)
                    .limit(pageSize)
                    .map(Cc -> ColecaoDTOResponse.valueOf(Cc))
                    .toList();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<ColecaoDTOResponse> findByNome(String nome){
        return repository
                    .findByNome(nome)
                    .stream()
                    .map(Cc -> ColecaoDTOResponse.valueOf(Cc))
                    .toList();
    }

    @Override
    public ColecaoDTOResponse findById(Long id){
        var colecao = repository.findById(id);
        if (colecao == null) {
            return null;
        }
        return ColecaoDTOResponse.valueOf(colecao);
    }

    @Override
    @Transactional
    public ColecaoDTOResponse create(ColecaoDTO dto){

        Colecao colecao = new Colecao();
        colecao.setNome(dto.nome());
        colecao.setProdutos(dto.produtos());
        repository.persist(colecao);
        return ColecaoDTOResponse.valueOf(colecao);
    }

    @Override
    @Transactional
    public void update(Long id, ColecaoDTO dto){
        Colecao colecao = repository.findById(id);
        if (colecao == null) {
            return;
        }
        colecao.setNome(dto.nome());
        colecao.setProdutos(dto.produtos());
        repository.persist(colecao);
    }

    @Override
    @Transactional
    public void delete(Long id){

        repository.deleteById(id);

    }
}
