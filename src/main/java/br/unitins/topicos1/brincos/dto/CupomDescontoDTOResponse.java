package br.unitins.topicos1.brincos.dto;

import java.time.LocalDateTime;

import br.unitins.topicos1.brincos.model.CupomDesconto;
import br.unitins.topicos1.brincos.model.TipoCupomDesconto;

public record CupomDescontoDTOResponse(
    Long id,
    String nome,
    LocalDateTime datafinal,
    Float valorDesconto,
    TipoCupomDesconto tipoCupomDesconto,
    String codigo,
    Float valorMinimo) {

    public static CupomDescontoDTOResponse valueOf(CupomDesconto cupomDesconto) {
        return new CupomDescontoDTOResponse(
            cupomDesconto.getId(),
            cupomDesconto.getNome(),
            cupomDesconto.getDatafinal(),
            cupomDesconto.getValorDesconto(),
            cupomDesconto.getTipoCupomDesconto(),
            cupomDesconto.getCodigo(),
            cupomDesconto.getValorMinimo()
        );
    }
}