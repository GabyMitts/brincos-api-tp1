package br.unitins.topicos1.brincos.service;

import java.util.List;

import br.unitins.topicos1.brincos.dto.EnderecoUsuarioDTO;
import br.unitins.topicos1.brincos.dto.EnderecoUsuarioDTOResponse;
import br.unitins.topicos1.brincos.model.EnderecoUsuario;
import br.unitins.topicos1.brincos.model.Usuario;
import br.unitins.topicos1.brincos.repository.EnderecoUsuarioRepository;
import br.unitins.topicos1.brincos.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;


@ApplicationScoped
public class EnderecoUsuarioServiceImpl implements EnderecoUsuarioService {

    @Inject
    EnderecoUsuarioRepository repository;

    @Inject 
    UsuarioRepository usr;


    @Override
    public List<EnderecoUsuarioDTOResponse> findAll(Long usuarioId){
        return repository
                    .listAll()
                    .stream()
                    .filter(e -> e.getUsuario() != null && e.getUsuario().getId().equals(usuarioId))
                    .map(C -> EnderecoUsuarioDTOResponse.valueOf(C))
                    .toList();
    }

    @Override
    public List<EnderecoUsuarioDTOResponse> findAll(){
        return repository
                    .listAll()
                    .stream()
                    .map(C -> EnderecoUsuarioDTOResponse.valueOf(C))
                    .toList();
    }

    @Override
    public EnderecoUsuarioDTOResponse findById(Long id) {
        return EnderecoUsuarioDTOResponse.valueOf(repository.findById(id));
    }

    @Override
    @Transactional
    public EnderecoUsuarioDTOResponse create(EnderecoUsuarioDTO dto) {
        EnderecoUsuario enderecoUsuario = new EnderecoUsuario();
        Usuario usuario = usr.findById(dto.usuarioId());
        enderecoUsuario.setUsuario(usuario);
        enderecoUsuario.setRua(dto.rua());
        enderecoUsuario.setNumero(dto.numero());
        enderecoUsuario.setComplemento(dto.complemento());
        enderecoUsuario.setBairro(dto.bairro());
        enderecoUsuario.setCidade(dto.cidade());
        enderecoUsuario.setEstado(dto.estado());
        enderecoUsuario.setCep(dto.cep());
        repository.persist(enderecoUsuario);
        // enderecoUsuario.setSenha(null);
        return EnderecoUsuarioDTOResponse.valueOf(enderecoUsuario);
    }

    @Override
    @Transactional
    public EnderecoUsuarioDTOResponse update(EnderecoUsuarioDTO dto) {
        EnderecoUsuario enderecoUsuario = repository.findById(dto.usuarioId());
        if (enderecoUsuario == null) {
            throw new NotFoundException("EnderecoUsuario not found");
        }
        Usuario usuario = usr.findById(dto.usuarioId());
        enderecoUsuario.setUsuario(usuario);
        enderecoUsuario.setRua(dto.rua());
        enderecoUsuario.setNumero(dto.numero());
        enderecoUsuario.setComplemento(dto.complemento());
        enderecoUsuario.setBairro(dto.bairro());
        enderecoUsuario.setCidade(dto.cidade());
        enderecoUsuario.setEstado(dto.estado());
        enderecoUsuario.setCep(dto.cep());
        repository.persist(enderecoUsuario);
        return EnderecoUsuarioDTOResponse.valueOf(enderecoUsuario);
    }

}
