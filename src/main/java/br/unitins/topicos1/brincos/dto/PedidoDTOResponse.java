package br.unitins.topicos1.brincos.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.topicos1.brincos.model.Pedido;

public record PedidoDTOResponse(
        Long id,
        LocalDateTime data,
        Float total,
        UsuarioDTOResponse usuario,
        List<ItemPedidoDTOResponse> itensPedido,
        EnderecoUsuarioDTOResponse endereco
        // CupomDescontoDTOResponse cupomDesconto
        ) {

    public static PedidoDTOResponse valueOf(Pedido pedido) {
        return new PedidoDTOResponse(
                pedido.getId(),
                pedido.getData(),
                pedido.getTotal(),
                UsuarioDTOResponse.valueOf(pedido.getUsuario()),
                pedido.getItensPedido().stream().map(ip -> ItemPedidoDTOResponse.valueOf(ip)).toList(),
                EnderecoUsuarioDTOResponse.valueOf(pedido.getEndereco())
                // CupomDescontoDTOResponse.valueOf(pedido.getCupomDesconto())
        );
    }
}