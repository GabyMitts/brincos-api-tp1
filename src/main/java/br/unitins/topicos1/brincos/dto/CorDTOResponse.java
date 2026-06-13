package br.unitins.topicos1.brincos.dto;

import br.unitins.topicos1.brincos.model.Cor;

public record CorDTOResponse(
    Long id,
    String nome
) {
    public static CorDTOResponse valueOf(Cor cor){
        return new CorDTOResponse(
            cor.getId(),
            cor.getNome());
    }
}
