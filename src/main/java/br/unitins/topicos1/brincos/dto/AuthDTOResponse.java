package br.unitins.topicos1.brincos.dto;

import br.unitins.topicos1.brincos.model.Usuario;

public record AuthDTOResponse(
    Long id,
    String email,
    String senha
) {  
    
    public static AuthDTOResponse valueOf(Usuario usuario) {
        return new AuthDTOResponse(
            usuario.getId(),
            usuario.getEmail(),
            usuario.getSenha()
        );
    }
}