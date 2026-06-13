package br.unitins.topicos1.brincos.dto;

import jakarta.validation.constraints.Email;

public record UsuarioDTO (
    String nome,
    @Email String email,
    String senha,
    Long idPerfil,
    String telefone,
    String tokenRecuperacaoSenha
) {
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Long getIdPerfil() {
        return idPerfil;
    }

    public String getTelefone() {
        return telefone;
    }
    public String getTokenRecuperacaoSenha() {
        return tokenRecuperacaoSenha;
    }

}