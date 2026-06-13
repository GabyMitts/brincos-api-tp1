package br.unitins.topicos1.brincos.service;

import java.util.List;

import br.unitins.topicos1.brincos.dto.BrincoDTO;
import br.unitins.topicos1.brincos.dto.BrincoDTOResponse;
import br.unitins.topicos1.brincos.model.Brinco;
import br.unitins.topicos1.brincos.model.Colecao;
import br.unitins.topicos1.brincos.model.Formato;
import br.unitins.topicos1.brincos.repository.BrincoRepository;
import br.unitins.topicos1.brincos.repository.ColecaoRepository;
import br.unitins.topicos1.brincos.repository.CorRepository;
import br.unitins.topicos1.brincos.repository.FormatoRepository;
import br.unitins.topicos1.brincos.repository.MaterialRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BrincoServiceImpl implements BrincoService{

    @Inject
    BrincoRepository repository;

    @Inject
    MaterialRepository repositoryMaterial;

    @Inject
    FormatoRepository repositoryFormato;

    @Inject
    ColecaoRepository repositoryColecao;

    @Inject
    CorRepository repositoryCor;

    @Override
    public List<BrincoDTOResponse> findAll( int page, int pageSize) {
           return repository
                    .listAll()
                    .stream()
                    .skip(page * pageSize)
                    .limit(pageSize)    
                    .map(B -> BrincoDTOResponse.valueOf(B))
                    .toList();
    }

    @Override 
    public long count() {
        return repository.count();
    }

    @Override
    public List<BrincoDTOResponse> findByCor(String cor) {
        return repository
                    .findByCor(cor)
                    .stream()
                    .map(B -> BrincoDTOResponse.valueOf(B))
                    .toList();
    }

    @Override
    public BrincoDTOResponse findById(Long id) {
        Brinco brinco = repository.findById(id);
        if (brinco == null) {
            return null;    
        }
        return BrincoDTOResponse.valueOf(brinco);
    }

    @Override
    @Transactional
    public BrincoDTOResponse create(BrincoDTO dto){

        Brinco brinco = new Brinco();
        brinco.setNome(dto.nome());
        brinco.setTamanho(dto.tamanho());

        dto.idsCores().forEach(idCor -> brinco.cor.add(repositoryCor.findById(idCor)));

        Formato formato = repositoryFormato.findById(dto.idFormato());
        brinco.setFormato(formato);

        Colecao colecao = repositoryColecao.findById(dto.idColecao());
        brinco.setColecao(colecao);

        dto.idsMateriais().forEach(idMaterial -> brinco.material.add(repositoryMaterial.findById(idMaterial)));

        repository.persist(brinco);

        return BrincoDTOResponse.valueOf(brinco);
    }

    @Override
    @Transactional
    public void update(Long id, BrincoDTO dto){

        Brinco edicaoBrinco = repository.findById(id);
        edicaoBrinco.setNome(dto.nome());
        edicaoBrinco.setTamanho(dto.tamanho());

        edicaoBrinco.cor.clear();
        dto.idsCores().forEach(idCor -> edicaoBrinco.cor.add(repositoryCor.findById(idCor)));

        Formato formato = repositoryFormato.findById(dto.idFormato());
        edicaoBrinco.setFormato(formato);

        Colecao colecao = repositoryColecao.findById(dto.idColecao());
        edicaoBrinco.setColecao(colecao);

        edicaoBrinco.material.clear();
        dto.idsMateriais().forEach(idMaterial -> edicaoBrinco.material.add(repositoryMaterial.findById(idMaterial)));
    }

    @Override
    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

}

