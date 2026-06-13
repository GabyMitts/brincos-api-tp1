package br.unitins.topicos1.brincos.service;

import java.util.List;

import br.unitins.topicos1.brincos.dto.FormatoDTO;
import br.unitins.topicos1.brincos.dto.FormatoDTOResponse;

public interface FormatoService {
    
    List<FormatoDTOResponse> findAll(int page, int pageSize);
    long count();
    List<FormatoDTOResponse> findByNome(String nome);
    FormatoDTOResponse findById(Long id);
    FormatoDTOResponse create(FormatoDTO dto);
    void update(Long id, FormatoDTO dto);
    void delete(Long id);
}
