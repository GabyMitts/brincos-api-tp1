package br.unitins.topicos1.brincos.dto;

import java.util.List;

public record BrincoDTO(
    String nome,
    Float tamanho,
    Float preco,
    Long idFormato,
    Long idColecao,
    List<Long> idsCores,
    List<Long> idsMateriais
) {

}
