package br.unitins.topicos1.brincos.service;

import java.util.List;

import br.unitins.topicos1.brincos.dto.PiercingDTO;
import br.unitins.topicos1.brincos.dto.PiercingDTOResponse;
import br.unitins.topicos1.brincos.model.LocalAplicacao;
import br.unitins.topicos1.brincos.model.Piercing;
import br.unitins.topicos1.brincos.model.Cor;
import br.unitins.topicos1.brincos.model.Formato;
import br.unitins.topicos1.brincos.model.Material;
import br.unitins.topicos1.brincos.repository.PiercingRepository;
import br.unitins.topicos1.brincos.repository.CorRepository;
import br.unitins.topicos1.brincos.repository.FormatoRepository;
import br.unitins.topicos1.brincos.repository.MaterialRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PiercingServiceImpl implements PiercingService{

    @Inject
    PiercingRepository repository;

    @Inject
    MaterialRepository repositoryMaterial;

    @Inject
    FormatoRepository repositoryFormato;

    @Inject
    CorRepository repositoryCor;

    @Override
    public List<PiercingDTOResponse> findAll() {
           return repository
                    .listAll()
                    .stream()
                    .map(B -> PiercingDTOResponse.valueOf(B))
                    .toList();
    }

    @Override
    public List<PiercingDTOResponse> findByCor(String cor) {
        return repository
                    .findByCor(cor)
                    .stream()
                    .map(B -> PiercingDTOResponse.valueOf(B))
                    .toList();
    }

    @Override
    public PiercingDTOResponse findById(Long id) {
        Piercing piercing = repository.findById(id);
        if (piercing == null) {
            return null;    
        }
        return PiercingDTOResponse.valueOf(piercing);
    }

    @Override
    @Transactional
    public PiercingDTOResponse create(PiercingDTO dto){

        Piercing piercing = new Piercing();
        piercing.setNome(dto.nome());
        piercing.setTamanho(dto.tamanho());
        piercing.setLocalAplicacao(LocalAplicacao.valueOf(dto.localAplicacao()));

        Cor cor = repositoryCor.findById(dto.idCor());
        piercing.cor.add(cor);

        Formato formato = repositoryFormato.findById(dto.idFormato());
        piercing.setFormato(formato);

        Material material = repositoryMaterial.findById(dto.idMaterial());
        piercing.material.add(material);

        repository.persist(piercing);

        return PiercingDTOResponse.valueOf(piercing);
    }

    @Override
    @Transactional
    public void update(Long id, PiercingDTO dto){

        Piercing edicaoPiercing = repository.findById(id);
        edicaoPiercing.setNome(dto.nome());
        edicaoPiercing.setTamanho(dto.tamanho());
        edicaoPiercing.setLocalAplicacao(LocalAplicacao.valueOf(dto.localAplicacao()));

        Cor cor = repositoryCor.findById(dto.idCor());
        edicaoPiercing.cor.add(cor);

        Formato formato = repositoryFormato.findById(dto.idFormato());
        edicaoPiercing.setFormato(formato);

        Material material = repositoryMaterial.findById(dto.idMaterial());
        edicaoPiercing.material.add(material);
    }

    @Override
    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

}

