package br.unitins.topicos1.brincos.dto;

import java.util.List;

public record PedidoDTO(
    List<ItemPedidoDTO> itensPedido,
    Long enderecoId,
    Long cupomDescontoId
) { }

