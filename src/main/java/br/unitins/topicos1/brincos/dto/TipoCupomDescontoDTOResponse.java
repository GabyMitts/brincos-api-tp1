package br.unitins.topicos1.brincos.dto;

public record TipoCupomDescontoDTOResponse(
    Long ID,
    String LABEL
) {
    public static TipoCupomDescontoDTOResponse valueOf(TipoCupomDesconto tipoCupomDesconto) {
        return new TipoCupomDescontoDTOResponse(
            tipoCupomDesconto.ID(),
            tipoCupomDesconto.LABEL()
        );
    }
}