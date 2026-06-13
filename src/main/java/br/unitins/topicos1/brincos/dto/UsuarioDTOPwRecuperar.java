package br.unitins.topicos1.brincos.dto;

import jakarta.validation.constraints.Email;

public record UsuarioDTOPwRecuperar (
    @Email String email,
    String novaSenha,
    String token
) {

}