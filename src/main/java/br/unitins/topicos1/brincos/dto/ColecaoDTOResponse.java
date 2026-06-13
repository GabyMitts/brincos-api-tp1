package br.unitins.topicos1.brincos.dto;

import java.util.Collections;
import java.util.List;

import br.unitins.topicos1.brincos.model.Colecao;

public record ColecaoDTOResponse(
    Long id,
    String nome,
    List<ProdutoDTOResponse> produtos
) {
    public static ColecaoDTOResponse valueOf(Colecao colecao){
        return new ColecaoDTOResponse(
            colecao.getId(),
            colecao.getNome(),
            colecao.getProdutos() != null 
                ? colecao.getProdutos().stream().map(ip -> ProdutoDTOResponse.valueOf(ip)).toList()
                : Collections.emptyList());
    }
}
