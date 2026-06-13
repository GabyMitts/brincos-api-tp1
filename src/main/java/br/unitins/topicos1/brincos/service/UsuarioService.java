package br.unitins.topicos1.brincos.service;

import java.util.List;
import java.util.Optional;

import br.unitins.topicos1.brincos.dto.UsuarioDTO;
import br.unitins.topicos1.brincos.dto.UsuarioDTOEmail;
import br.unitins.topicos1.brincos.dto.UsuarioDTOResponse;
import br.unitins.topicos1.brincos.model.Usuario;

public interface UsuarioService {
    List<UsuarioDTOResponse> findAll();
    Optional<Usuario> findByEmail(String email);
    Usuario findByEmailSenha(String email, String senha);
    Usuario findById(Long id);
    UsuarioDTOResponse update(Long id, UsuarioDTO dto);
    UsuarioDTOResponse create(UsuarioDTO dto);
    UsuarioDTOResponse createAdmin(UsuarioDTO dto);
    Boolean getTokenRecuperacaoSenha(UsuarioDTOEmail email);
    Boolean recuperarSenha(String email, String novaSenha, String token);
    Boolean resetarSenha(String email, String senha, String novaSenha);
    
}