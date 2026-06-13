package br.unitins.topicos1.brincos.service;

import java.util.List;

import br.unitins.topicos1.brincos.dto.PiercingDTO;
import br.unitins.topicos1.brincos.dto.PiercingDTOResponse;

public interface PiercingService {
    List<PiercingDTOResponse> findAll();
    List<PiercingDTOResponse> findByCor(String cor);
    PiercingDTOResponse findById(Long id);
    PiercingDTOResponse create(PiercingDTO dto);
    void update(Long id, PiercingDTO dto);
    void delete(Long id);
    
}
