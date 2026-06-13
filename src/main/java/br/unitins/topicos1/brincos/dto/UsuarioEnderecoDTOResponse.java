package br.unitins.topicos1.brincos.dto;

import br.unitins.topicos1.brincos.model.Perfil;
import br.unitins.topicos1.brincos.model.Usuario;



public record UsuarioEnderecoDTOResponse(Long id, String email, String telefone, Perfil perfil, EnderecoUsuarioDTOResponse[] enderecos) {

    public static UsuarioEnderecoDTOResponse valueOf(Usuario usuario) {
        if (usuario == null)
            return null;
        
        return new UsuarioEnderecoDTOResponse (
            usuario.getId(), 
            usuario.getEmail(), 
            usuario.getTelefone(),
            usuario.getPerfil(),
            usuario.getEnderecos().stream().map(id -> EnderecoUsuarioDTOResponse.valueOf(id)).toArray(EnderecoUsuarioDTOResponse[]::new)
        );

    }
    
}