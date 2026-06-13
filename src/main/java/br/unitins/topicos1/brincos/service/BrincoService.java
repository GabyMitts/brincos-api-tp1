package br.unitins.topicos1.brincos.service;

import java.util.List;

import br.unitins.topicos1.brincos.dto.BrincoDTO;
import br.unitins.topicos1.brincos.dto.BrincoDTOResponse;

public interface BrincoService {
    List<BrincoDTOResponse> findAll(int page, int pageSize);
    long count();
    List<BrincoDTOResponse> findByCor(String cor);
    BrincoDTOResponse findById(Long id);
    BrincoDTOResponse create(BrincoDTO dto);
    void update(Long id, BrincoDTO dto);
    void delete(Long id);
    
}
