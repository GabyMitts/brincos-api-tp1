package br.unitins.topicos1.brincos.dto;

public record PiercingDTO(
    String nome,
    Float tamanho,
    Long idFormato,
    Long idCor,
    Long idMaterial,
    String localAplicacao
) {

}
