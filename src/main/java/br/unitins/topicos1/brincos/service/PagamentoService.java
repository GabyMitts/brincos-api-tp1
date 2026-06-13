package br.unitins.topicos1.brincos.service;

import java.util.List;

import br.unitins.topicos1.brincos.dto.PagamentoDTO;
import br.unitins.topicos1.brincos.dto.PagamentoDTOResponse;


public interface PagamentoService {
    List<PagamentoDTOResponse> getAll();
    PagamentoDTOResponse findById(Long id);
    List<PagamentoDTOResponse> findByUsuario(String email);
    PagamentoDTOResponse create(PagamentoDTO dto, String email);
}