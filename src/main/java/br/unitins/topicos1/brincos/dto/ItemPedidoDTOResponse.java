package br.unitins.topicos1.brincos.dto;

import br.unitins.topicos1.brincos.model.ItemPedido;

public record ItemPedidoDTOResponse(
        Long id,
        Integer quantidade,
        Float preco,
        ProdutoDTOResponse produto) {

    public static ItemPedidoDTOResponse valueOf(ItemPedido itemPedido) {
        return new ItemPedidoDTOResponse(
                itemPedido.getId(),
                itemPedido.getQuantidade(),
                itemPedido.getPreco(),
                ProdutoDTOResponse.valueOf(itemPedido.getProduto()));
    }
}