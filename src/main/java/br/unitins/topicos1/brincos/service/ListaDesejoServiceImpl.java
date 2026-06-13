package br.unitins.topicos1.brincos.service;

// import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.brincos.dto.ListaDesejoDTO;
import br.unitins.topicos1.brincos.dto.ListaDesejoDTOResponse;
import br.unitins.topicos1.brincos.model.ListaDesejo;
import br.unitins.topicos1.brincos.model.Usuario;
import br.unitins.topicos1.brincos.repository.ListaDesejoRepository;
import br.unitins.topicos1.brincos.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ListaDesejoServiceImpl implements ListaDesejoService {

    @Inject
    ListaDesejoRepository listaDesejoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    // @Inject
    // ProdutoRepository produtoRepository;


    @Override
    public List<ListaDesejoDTOResponse> getAll() {
        return listaDesejoRepository.findAll().list().stream().map(ListaDesejoDTOResponse::valueOf).toList();
    }

    @Override
    public ListaDesejoDTOResponse findById(Long id) {
        ListaDesejo listaDesejo = listaDesejoRepository.findById(id);
        if (listaDesejo == null)
            throw new NotFoundException("Lista de desejo não encontrada.");
        return ListaDesejoDTOResponse.valueOf(listaDesejo);
    }

    @Override
    @Transactional
    public ListaDesejoDTOResponse create(ListaDesejoDTO dto, String email) throws ConstraintViolationException {
    
        ListaDesejo entity = new ListaDesejo();
        System.out.println("Email recebido: " + email); // Log para verificar o email
        entity.setUsuario(usuarioRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Usuário não encontrado. " + email)));
        entity.setNome(dto.nome());
        listaDesejoRepository.persist(entity);
        if (entity.getId() == null) {
            throw new RuntimeException("Erro ao criar a lista de desejo.");
        }

        listaDesejoRepository.persist(entity);

        return ListaDesejoDTOResponse.valueOf(entity);
    }

    @Override
    public List<ListaDesejoDTOResponse> findByUsuario(String email){
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
        return listaDesejoRepository.findByUsuario(usuario).list().stream().map(e -> ListaDesejoDTOResponse.valueOf(e)).toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ListaDesejo listaDesejo = listaDesejoRepository.findById(id);
        if (listaDesejo == null)            throw new NotFoundException("Lista de desejo não encontrada.");
        listaDesejoRepository.delete(listaDesejo);
    }

}