package br.unitins.topicos1.brincos.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.topicos1.brincos.model.EnderecoUsuario;

public record PedidoDTOSave(
    List<ItemPedidoDTO> itensPedido,
    Long enderecoId,
    Long status,
    LocalDateTime data,
    Double total,
    EnderecoUsuario endereco
) { }


