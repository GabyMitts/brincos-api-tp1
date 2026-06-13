package br.unitins.topicos1.brincos.dto;

import java.util.List;

import br.unitins.topicos1.brincos.model.Produto;

public record ColecaoDTO(
    String nome,
    List<Produto> produtos
) {}
