package br.unitins.topicos1.brincos.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.brincos.dto.ItemPedidoDTO;
import br.unitins.topicos1.brincos.dto.PedidoDTO;
import br.unitins.topicos1.brincos.dto.PedidoDTOResponse;
import br.unitins.topicos1.brincos.model.CupomDesconto;
import br.unitins.topicos1.brincos.model.EnderecoUsuario;
import br.unitins.topicos1.brincos.model.ItemPedido;
import br.unitins.topicos1.brincos.model.Pedido;
import br.unitins.topicos1.brincos.model.Usuario;
import br.unitins.topicos1.brincos.repository.CupomDescontoRepository;
import br.unitins.topicos1.brincos.repository.EnderecoUsuarioRepository;
import br.unitins.topicos1.brincos.repository.PedidoRepository;
import br.unitins.topicos1.brincos.repository.ProdutoRepository;
import br.unitins.topicos1.brincos.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    EnderecoUsuarioRepository enderecoUsuarioRepository;

    @Inject
    ProdutoRepository produtoRepository;

    @Inject
    CupomDescontoRepository cupomDescontoRepository;

    @Override
    public List<PedidoDTOResponse> getAll() {
        return pedidoRepository.findAll().list().stream().map(p -> PedidoDTOResponse.valueOf(p)).toList();
    }

    @Override
    public PedidoDTOResponse findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id);
        if (pedido == null)
            throw new NotFoundException("Pedido não encontrada.");
        return PedidoDTOResponse.valueOf(pedido);
    }

    @Override
    @Transactional
    public PedidoDTOResponse create(PedidoDTO pedidoDTO, String email) throws ConstraintViolationException {
        EnderecoUsuario endereco = enderecoUsuarioRepository.findById(pedidoDTO.enderecoId());
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço não encontrado.");
        }
        if (pedidoDTO.itensPedido() == null || pedidoDTO.itensPedido().isEmpty()) {
            throw new IllegalArgumentException("O pedido deve conter pelo menos um item.");
        }
        CupomDesconto cupomDesconto = null;
        if (pedidoDTO.cupomDescontoId() != null) {
             cupomDesconto = cupomDescontoRepository.findByCodigo(pedidoDTO.cupomDescontoId().toString());
            if (cupomDesconto == null) {
                throw new IllegalArgumentException("Cupom de desconto não encontrado: " + pedidoDTO.cupomDescontoId());
            }
            Pedido pedidoExistente = pedidoRepository.findByCupomDesconto(cupomDesconto, usuarioRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado. " + email))).firstResult();
            if (pedidoExistente != null) {
                throw new IllegalArgumentException("Já existe um pedido com o cupom de desconto: " + pedidoDTO.cupomDescontoId());
            }   
        }

        Pedido entity = new Pedido();
        entity.setData(LocalDateTime.now());
        entity.setEndereco(endereco);
        entity.setUsuario(usuarioRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado. " + email)));
        entity.setStatus(0);
        pedidoRepository.persist(entity);
        if (entity.getId() == null) {
            throw new RuntimeException("Erro ao criar o pedido.");
        }

        List<ItemPedido> itensPedido = new ArrayList<>();
        Float totalPedido = 0f;
        for (ItemPedidoDTO itemDTO : pedidoDTO.itensPedido()) {
            ItemPedido ip = new ItemPedido();
            ip.setPedido(entity);
            
            var produto = produtoRepository.findById(itemDTO.idProduto());
            if (produto == null) {
                throw new IllegalArgumentException("Produto com ID " + itemDTO.idProduto() + " não encontrado.");
            }
            ip.setProduto(produto);
            ip.setPreco(produto.getPreco());
            if (itemDTO.quantidade() <= 0) {
                throw new IllegalArgumentException("A quantidade deve ser maior que zero para o produto: " + produto.getNome());
            }
            Float totalItem = 0f;
            totalItem = itemDTO.quantidade() * produto.getPreco();
            totalPedido += totalItem;
            
            if (ip.getProduto().getEstoque() < itemDTO.quantidade()) {
                throw new IllegalArgumentException("Estoque insuficiente para o produto: " + ip.getProduto().getNome());
            }
            ip.getProduto().setEstoque(ip.getProduto().getEstoque() - itemDTO.quantidade());
            ip.setQuantidade(itemDTO.quantidade());
            produtoRepository.persist(ip.getProduto());
            itensPedido.add(ip);
        }

        if (cupomDesconto != null && cupomDesconto.getDatafinal().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("O cupom de desconto expirou: " + cupomDesconto.getCodigo());
        }

        if (cupomDesconto != null && cupomDesconto.getValorMinimo() > totalPedido) {
            throw new IllegalArgumentException("Não é possível aplicar o cupom de desconto neste valor de compra: " + cupomDesconto.getCodigo());
        }

        if (cupomDesconto != null) {
            if (cupomDesconto.getTipoCupomDesconto().ID == 1) { // Desconto percentual
                totalPedido = totalPedido - (totalPedido * (cupomDesconto.getValorDesconto() / 100));
            } else if (cupomDesconto.getTipoCupomDesconto().ID == 2) { // Desconto fixo
                totalPedido = totalPedido - cupomDesconto.getValorDesconto();
            }
        }

        entity.setTotal(totalPedido);
        entity.setCupomDesconto(cupomDesconto);
        entity.setItensPedido(itensPedido);
        pedidoRepository.persist(entity);
        return PedidoDTOResponse.valueOf(entity);
    }

    @Override
    public List<PedidoDTOResponse> findByUsuario(String email){
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
        return pedidoRepository.findByUsuario(usuario).list().stream().map(e -> PedidoDTOResponse.valueOf(e)).toList();
    }

    @Override
    public PedidoDTOResponse findByCupomDesconto(String codigo, String email, Float valorTotal) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
        CupomDesconto cupomDesconto = cupomDescontoRepository.findByCodigo(codigo);
        System.out.println("Usuário encontrado: " + usuario.getEmail());
        System.out.println("Cupom encontrado: " + (cupomDesconto != null ? cupomDesconto.getCodigo() : "null"));
        if (cupomDesconto == null) {
            throw new NotFoundException("Cupom de desconto não encontrado: " + codigo);
        }
        if (cupomDesconto.getValorMinimo() > valorTotal) {
            throw new IllegalArgumentException("Não é possível aplicar o cupom de desconto neste valor de compra: " + codigo);
        }
        var pedidos = pedidoRepository.findByCupomDesconto(cupomDesconto, usuario).list();
        if (!pedidos.isEmpty()) {
            throw new NotFoundException("Pedido já utilizado pelo usuario: " + codigo);
        }
        return null;
    }

}