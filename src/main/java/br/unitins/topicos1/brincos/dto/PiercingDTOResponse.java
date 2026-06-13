package br.unitins.topicos1.brincos.dto;

import br.unitins.topicos1.brincos.model.Piercing;

public record PiercingDTOResponse(
    Long id,
    String nome,
    Float tamanho,
    String nomeFormato,
    String cor,
    String nomeMaterial,
    String localAplicacao
) {
    public static PiercingDTOResponse valueOf(Piercing piercing){
        return new PiercingDTOResponse(
            piercing.getId(),
            piercing.getNome(),
            piercing.getTamanho(),
            piercing.getFormato().toString(),
            piercing.cor.iterator().next().getNome(),
            piercing.material.iterator().next().getNome(),
            piercing.getLocalAplicacao().toString());
    }
}