package br.unitins.topicos1.brincos.dto;

import java.time.LocalDateTime;

import br.unitins.topicos1.brincos.model.TipoCupomDesconto;

public record CupomDescontoDTO (
    String nome,
    LocalDateTime datafinal,
    Float valorDesconto,
    TipoCupomDesconto tipoCupomDesconto,
    String codigo,
    Float valorMinimo

) {

}