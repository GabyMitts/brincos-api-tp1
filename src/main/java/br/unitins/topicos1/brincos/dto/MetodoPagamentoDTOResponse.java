package br.unitins.topicos1.brincos.dto;


import br.unitins.topicos1.brincos.model.MetodoPagamento;

public record MetodoPagamentoDTOResponse(
    Long ID,
    String LABEL
) {
    public static MetodoPagamentoDTOResponse valueOf(MetodoPagamento metodoPagamento) {
        return new MetodoPagamentoDTOResponse(
            metodoPagamento.ID,
            metodoPagamento.LABEL
        );
    }
}