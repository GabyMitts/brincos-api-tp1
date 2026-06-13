package br.unitins.topicos1.brincos.service;

import java.util.List;

import br.unitins.topicos1.brincos.dto.ItemListaDesejoDTO;
import br.unitins.topicos1.brincos.dto.ItemListaDesejoDTOResponse;
    

public interface ItemListaDesejoService {
    List<ItemListaDesejoDTOResponse> getAll(Long idLista, String email);
    ItemListaDesejoDTOResponse findById(Long id, Long idLista, String email);
    ItemListaDesejoDTOResponse create(ItemListaDesejoDTO dto, Long idLista, String email);
    void delete(Long id, String email);
}