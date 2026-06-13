package br.unitins.topicos1.brincos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCrypt;

import br.unitins.topicos1.brincos.dto.UsuarioDTO;
import br.unitins.topicos1.brincos.dto.UsuarioDTOEmail;
import br.unitins.topicos1.brincos.dto.UsuarioDTOResponse;
import br.unitins.topicos1.brincos.model.Perfil;
import br.unitins.topicos1.brincos.model.Usuario;
import br.unitins.topicos1.brincos.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;


    @Override
    public List<UsuarioDTOResponse> findAll(){
        return repository
                    .listAll()
                    .stream()
                    .map(C -> UsuarioDTOResponse.valueOf(C))
                    .toList();
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Usuario findByEmailSenha(String email, String senha) {
        return repository.findByEmailSenha(email, senha);
    }

    @Override
    public Usuario findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public UsuarioDTOResponse update(Long id, UsuarioDTO dto) {
        Usuario usuario = repository.findById(id);
        if (usuario == null) {
            return null;
        }
        usuario.setEmail(dto.getEmail());
        usuario.setNome(dto.getNome());
        usuario.setTelefone(dto.getTelefone());
        usuario.setPerfil(Perfil.valueOf(dto.getIdPerfil()));
        repository.persist(usuario);
        return UsuarioDTOResponse.valueOf(usuario);
    }   

    @Override
    @Transactional
    public UsuarioDTOResponse create(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setNome(dto.getNome());
        usuario.setSenha(BCrypt.hashpw(dto.getSenha(), BCrypt.gensalt()));
        usuario.setTelefone(dto.getTelefone());
        usuario.setPerfil(Perfil.Client);
        repository.persist(usuario);
        return UsuarioDTOResponse.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioDTOResponse createAdmin(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setNome(dto.getNome());
        usuario.setSenha(BCrypt.hashpw(dto.getSenha(), BCrypt.gensalt()));
        usuario.setTelefone(dto.getTelefone());
        usuario.setPerfil(Perfil.valueOf(dto.getIdPerfil()));
        repository.persist(usuario);
        return UsuarioDTOResponse.valueOf(usuario);
    }

    @Override
    @Transactional
    public Boolean getTokenRecuperacaoSenha( UsuarioDTOEmail email) {
        System.out.println("EMAIL: " + email.email());
        Usuario usuario = repository.findByEmail(email.email()).orElse(null);
        if (usuario == null) {
            return true;
        }
        try {

            String token = java.util.UUID.randomUUID().toString();
            String criptToken = BCrypt.hashpw(token, BCrypt.gensalt());
            usuario.setTokenRecuperacaoSenha(criptToken);
            // Aqui seria enviado o email com o token para o usuário
            System.out.println("TOKEN: " + token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean recuperarSenha(String email, String novaSenha, String token) {
        Usuario usuario = repository.findByEmail(email).orElse(null); 
        try {
            if (usuario == null || !BCrypt.checkpw(token, usuario.getTokenRecuperacaoSenha())) {
                return false;
            }
            usuario.setSenha(BCrypt.hashpw(novaSenha, BCrypt.gensalt()));
            usuario.setTokenRecuperacaoSenha(null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean resetarSenha(String email, String senha, String novaSenha) {
        Usuario usuario = repository.findByEmail(email).orElse(null); 
        try {
            if (usuario == null || !BCrypt.checkpw(senha, usuario.getSenha())) {
                return false;
            }
            usuario.setSenha(BCrypt.hashpw(novaSenha, BCrypt.gensalt()));
            return true;
        } catch (Exception e) {
            return false;
        }   
    }
}
