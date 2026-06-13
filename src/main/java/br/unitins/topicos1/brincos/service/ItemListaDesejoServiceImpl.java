package br.unitins.topicos1.brincos.service;

import java.util.List;

import br.unitins.topicos1.brincos.dto.ItemListaDesejoDTO;
import br.unitins.topicos1.brincos.dto.ItemListaDesejoDTOResponse;
import br.unitins.topicos1.brincos.model.ItemListaDesejo;
import br.unitins.topicos1.brincos.model.ListaDesejo;
import br.unitins.topicos1.brincos.model.Produto;
import br.unitins.topicos1.brincos.model.Usuario;
import br.unitins.topicos1.brincos.repository.ItensListaDesejoRepository;
import br.unitins.topicos1.brincos.repository.ListaDesejoRepository;
import br.unitins.topicos1.brincos.repository.ProdutoRepository;
import br.unitins.topicos1.brincos.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ItemListaDesejoServiceImpl implements ItemListaDesejoService {

    @Inject
    ListaDesejoRepository listaDesejoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    ProdutoRepository produtoRepository;

    @Inject
    ItensListaDesejoRepository itemListaDesejoRepository;

    @Override
    public List<ItemListaDesejoDTOResponse> getAll(Long idLista, String email) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

        ListaDesejo listaDesejo = listaDesejoRepository.findByUsuario(usuario).stream().filter(e -> e.getId().equals(idLista)).findFirst().orElse(null);
        if (listaDesejo == null)
            throw new NotFoundException("Lista de desejo não encontrada."); 

        System.out.println(usuario);
        return itemListaDesejoRepository.findByLista(idLista, usuario).stream().map(ItemListaDesejoDTOResponse::valueOf).toList();
    }

    @Override
    public ItemListaDesejoDTOResponse findById(Long id, Long idLista, String email) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

        ListaDesejo listaDesejo = listaDesejoRepository.findByUsuario(usuario).stream().filter(e -> e.getId().equals(idLista)).findFirst().orElse(null);
        if (listaDesejo == null)
            throw new NotFoundException("Lista de desejo não encontrada."); 
        
        ItemListaDesejo itemListaDesejo = itemListaDesejoRepository.findById(id);
        if (itemListaDesejo == null)
            throw new NotFoundException("Item da lista de desejo não encontrado.");

        return ItemListaDesejoDTOResponse.valueOf(itemListaDesejo);
    }

    @Override
    @Transactional
    public ItemListaDesejoDTOResponse create(ItemListaDesejoDTO dto, Long idLista, String email) throws ConstraintViolationException {
    
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

        ListaDesejo listaDesejo = listaDesejoRepository.findByUsuario(usuario).stream().filter(e -> e.getId().equals(idLista)).findFirst().orElse(null);
        if (listaDesejo == null)
            throw new NotFoundException("Lista de desejo não encontrada."); 

        Produto produto = produtoRepository.findById(dto.idProduto());
        if (produto == null)
            throw new NotFoundException("Produto não encontrado.");

        ItemListaDesejo entity = new ItemListaDesejo();
        entity.setProduto(produto);
        entity.setPreco(produto.getPreco());
        entity.setListaDesejo(listaDesejo);

        itemListaDesejoRepository.persist(entity);

        if (entity.getId() == null) {
            throw new RuntimeException("Erro ao criar a lista de desejo.");
        }

        itemListaDesejoRepository.persist(entity);

        return ItemListaDesejoDTOResponse.valueOf(entity);
    }

    @Override
    @Transactional
    public void delete(Long id, String email) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
        
        ItemListaDesejo itemListaDesejo = itemListaDesejoRepository.findById(id);
        if (itemListaDesejo == null)
            throw new NotFoundException("Item da lista de desejo não encontrado."); 

        ListaDesejo listaDesejo = listaDesejoRepository.findByUsuario(usuario).stream().filter(e -> e.getId().equals(itemListaDesejo.getListaDesejo().getId())).findFirst().orElse(null);
        if (listaDesejo == null)
            throw new NotFoundException("Lista de desejo não encontrada.");

        itemListaDesejoRepository.delete(itemListaDesejo);
    }
    
}