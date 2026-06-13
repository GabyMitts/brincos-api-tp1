package br.unitins.topicos1.brincos.dto;

import jakarta.validation.constraints.Email;

public record UsuarioDTOEmail (
    @Email String email
) {

}