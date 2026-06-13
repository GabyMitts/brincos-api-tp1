package br.unitins.topicos1.brincos.dto;

import br.unitins.topicos1.brincos.model.ListaDesejo;

public record ListaDesejoDTOResponse(
        Long id,
        String nome
        // List<ItemListaDesejoDTOResponse> itensListaDesejo
) {
    public static ListaDesejoDTOResponse valueOf(ListaDesejo listaDesejo) {
        return new ListaDesejoDTOResponse(
            listaDesejo.getId(),
            listaDesejo.getNome()
        );
    }
}
// listaDesejo.getItensListaDesejo().stream().map(ItemListaDesejoDTOResponse::valueOf).toList()