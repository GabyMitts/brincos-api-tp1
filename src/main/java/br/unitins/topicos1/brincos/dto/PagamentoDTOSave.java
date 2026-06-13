package br.unitins.topicos1.brincos.dto;

import java.time.LocalDateTime;

import br.unitins.topicos1.brincos.model.MetodoPagamento;

public record PagamentoDTOSave (
    Float total,
    LocalDateTime data,
    MetodoPagamento metodoPagamento,
    Long pedidoId,
    Boolean pago
) {

}