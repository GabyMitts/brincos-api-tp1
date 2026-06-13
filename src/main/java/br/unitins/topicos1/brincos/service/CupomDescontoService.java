package br.unitins.topicos1.brincos.service;

import java.util.List;

import br.unitins.topicos1.brincos.dto.CupomDescontoDTO;
import br.unitins.topicos1.brincos.dto.CupomDescontoDTOResponse;

public interface CupomDescontoService {
    
    List<CupomDescontoDTOResponse> findAll(int page, int pageSize);
    long count();
    List<CupomDescontoDTOResponse> findByNome(String nome);
    CupomDescontoDTOResponse findById(Long id);
    CupomDescontoDTOResponse create(CupomDescontoDTO dto);
    void update(Long id, CupomDescontoDTO dto);
    void delete(Long id);
}
