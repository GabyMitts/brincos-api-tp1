package br.unitins.topicos1.brincos.dto;

import br.unitins.topicos1.brincos.model.Material;

public record MaterialDTOResponse(
    Long id,
    String nome
) {
    public static MaterialDTOResponse valueOf(Material material){
        return new MaterialDTOResponse(
            material.getId(),
            material.getNome());
    }
}
