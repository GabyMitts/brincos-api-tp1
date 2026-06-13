package br.unitins.topicos1.brincos.dto;

public record EnderecoUsuarioDTO(
    Long usuarioId,
    String rua,
    String numero,
    String complemento,
    String bairro,
    String cidade,
    String estado,
    String pais,
    String cep
) { }
