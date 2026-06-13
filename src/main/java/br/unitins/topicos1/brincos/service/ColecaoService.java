package br.unitins.topicos1.brincos.service;

import java.util.List;

import br.unitins.topicos1.brincos.dto.ColecaoDTO;
import br.unitins.topicos1.brincos.dto.ColecaoDTOResponse;

public interface ColecaoService {
    
    List<ColecaoDTOResponse> findAll(int page, int pageSize);
    long count();
    List<ColecaoDTOResponse> findByNome(String nome);
    ColecaoDTOResponse findById(Long id);
    ColecaoDTOResponse create(ColecaoDTO dto);
    void update(Long id, ColecaoDTO dto);
    void delete(Long id);
}
