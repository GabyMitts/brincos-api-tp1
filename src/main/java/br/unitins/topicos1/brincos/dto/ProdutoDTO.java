package br.unitins.topicos1.brincos.dto;

public record ProdutoDTO(
    String nome,
    Float preco,
    Integer estoque,
    Float tamanho
) {

}
