package br.unitins.topicos1.brincos.service;

import java.util.List;

import br.unitins.topicos1.brincos.dto.MetodoPagamentoDTOResponse;


public interface MetodoPagamentoService {
    List<MetodoPagamentoDTOResponse> getAll();
    MetodoPagamentoDTOResponse findById(Long id);
}