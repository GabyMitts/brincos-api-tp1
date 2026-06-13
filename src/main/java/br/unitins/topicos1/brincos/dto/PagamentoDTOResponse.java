package br.unitins.topicos1.brincos.dto;

import java.time.LocalDateTime;

import br.unitins.topicos1.brincos.model.MetodoPagamento;
import br.unitins.topicos1.brincos.model.Pagamento;

public record PagamentoDTOResponse(
    Long id,
    LocalDateTime data,
    Float total,
    MetodoPagamento metodoPagamento,
    Boolean pago,
    Long pedidoId) {

    public static PagamentoDTOResponse valueOf(Pagamento pagamento) {
        return new PagamentoDTOResponse(
                pagamento.getId(),
                pagamento.getData(),
                pagamento.getTotal(),
                pagamento.getMetodoPagamento(),
                pagamento.getPago(),
                pagamento.getPedido().getId()
               );
    }
}