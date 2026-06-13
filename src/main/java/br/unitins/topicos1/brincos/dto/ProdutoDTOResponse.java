package br.unitins.topicos1.brincos.dto;

import br.unitins.topicos1.brincos.model.Produto;

public record ProdutoDTOResponse(
    Long id,
    String nome,
    Float preco,
    Integer estoque
) {
 public static ProdutoDTOResponse valueOf(Produto produto){
        return new ProdutoDTOResponse(
            produto.getId(),
            produto.getNome(),
            produto.getPreco(),
            produto.getEstoque() 
        );
    }
}
