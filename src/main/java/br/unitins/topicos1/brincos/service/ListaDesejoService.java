package br.unitins.topicos1.brincos.service;

import java.util.List;

import br.unitins.topicos1.brincos.dto.ListaDesejoDTO;
import br.unitins.topicos1.brincos.dto.ListaDesejoDTOResponse;
    

public interface ListaDesejoService {
    List<ListaDesejoDTOResponse> getAll();
    ListaDesejoDTOResponse findById(Long id);
    List<ListaDesejoDTOResponse> findByUsuario(String email);
    ListaDesejoDTOResponse create(ListaDesejoDTO dto, String email);
    void delete(Long id);
}