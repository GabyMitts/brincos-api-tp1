package br.unitins.topicos1.brincos.dto;

import br.unitins.topicos1.brincos.model.Perfil;
import br.unitins.topicos1.brincos.model.Usuario;


public record UsuarioDTOResponse(Long id, String nome, String email, String telefone, Perfil perfil) {

    public static UsuarioDTOResponse valueOf(Usuario usuario) {
        if (usuario == null)
            return null;
        
        return new UsuarioDTOResponse (
            usuario.getId(), 
            usuario.getNome(),
            usuario.getEmail(), 
            usuario.getTelefone(),
            usuario.getPerfil());

    }
    
}