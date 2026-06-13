package br.unitins.topicos1.brincos.dto;

import br.unitins.topicos1.brincos.model.EnderecoUsuario;

public record EnderecoUsuarioDTOResponse(
        Long id,
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String pais,
        String cep,
        UsuarioDTOResponse usuario) {

    public static EnderecoUsuarioDTOResponse valueOf(EnderecoUsuario enderecoUsuario) {
        return new EnderecoUsuarioDTOResponse(
                enderecoUsuario.getId(),
                enderecoUsuario.getRua(),
                enderecoUsuario.getNumero(),
                enderecoUsuario.getComplemento(),
                enderecoUsuario.getBairro(),
                enderecoUsuario.getCidade(),
                enderecoUsuario.getEstado(),
                enderecoUsuario.getPais(),
                enderecoUsuario.getCep(),
                UsuarioDTOResponse.valueOf(enderecoUsuario.getUsuario()));
                
    }
}
 