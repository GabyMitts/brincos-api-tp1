package br.unitins.topicos1.brincos.service;

import java.util.List;

import br.unitins.topicos1.brincos.dto.EnderecoUsuarioDTO;
import br.unitins.topicos1.brincos.dto.EnderecoUsuarioDTOResponse;

public interface EnderecoUsuarioService {
    List<EnderecoUsuarioDTOResponse> findAll(Long usuarioId);
    List<EnderecoUsuarioDTOResponse> findAll();
    EnderecoUsuarioDTOResponse findById(Long id);
    EnderecoUsuarioDTOResponse create(EnderecoUsuarioDTO dto);
    EnderecoUsuarioDTOResponse update(EnderecoUsuarioDTO dto);
}