package br.unitins.topicos1.brincos.dto;

import br.unitins.topicos1.brincos.model.Formato;

public record FormatoDTOResponse(
    Long id,
    String nome
) {
    public static FormatoDTOResponse valueOf(Formato formato){
        return new FormatoDTOResponse(
            formato.getId(),
            formato.getNome());
    }
}
