package br.unitins.topicos1.brincos.service;

import java.util.List;

import br.unitins.topicos1.brincos.dto.CorDTO;
import br.unitins.topicos1.brincos.dto.CorDTOResponse;

public interface CorService {
    
    List<CorDTOResponse> findAll(int page, int pageSize);
    long count();
    List<CorDTOResponse> findByNome(String nome);
    CorDTOResponse findById(Long id);
    CorDTOResponse create(CorDTO dto);
    void update(Long id, CorDTO dto);
    void delete(Long id);
}
