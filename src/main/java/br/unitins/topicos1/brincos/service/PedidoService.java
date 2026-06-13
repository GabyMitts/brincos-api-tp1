package br.unitins.topicos1.brincos.service;

import java.util.List;

import br.unitins.topicos1.brincos.dto.PedidoDTO;
import br.unitins.topicos1.brincos.dto.PedidoDTOResponse;


public interface PedidoService {
    List<PedidoDTOResponse> getAll();
    PedidoDTOResponse findById(Long id);
    List<PedidoDTOResponse> findByUsuario(String email);
    PedidoDTOResponse create(PedidoDTO dto, String email);
    PedidoDTOResponse findByCupomDesconto(String codigo, String email, Float valorTotal);
}