package br.unitins.topicos1.brincos.dto;

import br.unitins.topicos1.brincos.model.ItemListaDesejo;

public record ItemListaDesejoDTOResponse(
        Long id,
        Float preco,
        ProdutoDTOResponse produto) {

    public static ItemListaDesejoDTOResponse valueOf(ItemListaDesejo itemListaDesejo) {
        return new ItemListaDesejoDTOResponse(
                itemListaDesejo.getId(),
                itemListaDesejo.getPreco(),
                ProdutoDTOResponse.valueOf(itemListaDesejo.getProduto()));
    }
}