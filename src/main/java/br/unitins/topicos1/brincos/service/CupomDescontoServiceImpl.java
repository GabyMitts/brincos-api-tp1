package br.unitins.topicos1.brincos.service;

import java.util.List;

import br.unitins.topicos1.brincos.dto.CupomDescontoDTO;
import br.unitins.topicos1.brincos.dto.CupomDescontoDTOResponse;
import br.unitins.topicos1.brincos.model.CupomDesconto;
import br.unitins.topicos1.brincos.repository.CupomDescontoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CupomDescontoServiceImpl implements CupomDescontoService {
    
    @Inject
    CupomDescontoRepository repository;

    @Override
    public List<CupomDescontoDTOResponse> findAll(int page, int pageSize){
        return repository
                    .listAll()
                    .stream()
                    .skip(page * pageSize)
                    .limit(pageSize)
                    .map(M -> CupomDescontoDTOResponse.valueOf(M))
                    .toList();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<CupomDescontoDTOResponse> findByNome(String nome){
        return repository
                    .findByNome(nome)
                    .stream()
                    .map(M -> CupomDescontoDTOResponse.valueOf(M))
                    .toList();
    }

    @Override
    public CupomDescontoDTOResponse findById(Long id){
        CupomDesconto cupomDesconto = repository.findById(id);
        if (cupomDesconto == null) {
            return null;    
        }
        return CupomDescontoDTOResponse.valueOf(cupomDesconto);
    }

    @Override
    @Transactional
    public CupomDescontoDTOResponse create(CupomDescontoDTO dto){

        CupomDesconto cupomDesconto = new CupomDesconto();
        cupomDesconto.setNome(dto.nome());
        cupomDesconto.setDatafinal(dto.datafinal());
        cupomDesconto.setValorDesconto(dto.valorDesconto());
        cupomDesconto.setTipoCupomDesconto(dto.tipoCupomDesconto());
        cupomDesconto.setCodigo(dto.codigo());
        cupomDesconto.setValorMinimo(dto.valorMinimo());
        repository.persist(cupomDesconto);
        return CupomDescontoDTOResponse.valueOf(cupomDesconto);
    }

    @Override
    @Transactional
    public void update(Long id, CupomDescontoDTO dto){
        
        CupomDesconto cupomDesconto = repository.findById(id);
        if (cupomDesconto == null) {
            return;
        }
        cupomDesconto.setNome(dto.nome());
    }

    @Override
    @Transactional
    public void delete(Long id){

        repository.deleteById(id);

    }
}
