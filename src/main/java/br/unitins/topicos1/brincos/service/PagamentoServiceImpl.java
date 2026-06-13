package br.unitins.topicos1.brincos.service;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.topicos1.brincos.dto.MetodoPagamentoDTOResponse;
import br.unitins.topicos1.brincos.dto.PagamentoDTO;
import br.unitins.topicos1.brincos.dto.PagamentoDTOResponse;
import br.unitins.topicos1.brincos.model.MetodoPagamento;
import br.unitins.topicos1.brincos.model.Pagamento;
import br.unitins.topicos1.brincos.model.Pedido;
import br.unitins.topicos1.brincos.repository.PagamentoRepository;
import br.unitins.topicos1.brincos.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService {

    @Inject
    PagamentoRepository pagamentoRepository;

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    MetodoPagamentoService metodoPagamentoService;


    @Override
    public List<PagamentoDTOResponse> getAll() {
        return pagamentoRepository.findAll().list().stream().map(p -> PagamentoDTOResponse.valueOf(p)).toList();
    }

    @Override
    public PagamentoDTOResponse findById(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id);
        if (pagamento == null)
            throw new NotFoundException("Pagamento não encontrado.");
        return PagamentoDTOResponse.valueOf(pagamento);
    }

    @Override
    @Transactional
    public PagamentoDTOResponse create(PagamentoDTO pagamentoDTO, String email) throws ConstraintViolationException {

        Pedido pedido = pedidoRepository.findById(pagamentoDTO.pedidoId());
        if (pedido == null) {
            throw new NotFoundException("Pedido com ID " + pagamentoDTO.pedidoId() + " não encontrado.");
        }

        if (!email.equals(pedido.getUsuario().getEmail())) {
             throw new IllegalArgumentException("O email do usuário não corresponde ao email do pedido.");
        }

        MetodoPagamentoDTOResponse metodoPagamento = metodoPagamentoService.findById(pagamentoDTO.metodoPagamentoId());
        if (metodoPagamento == null) {
            throw new NotFoundException("Método de pagamento com ID " + pagamentoDTO.metodoPagamentoId() + " não encontrado.");
        }
        
        MetodoPagamento metodo = MetodoPagamento.valueOf(pagamentoDTO.metodoPagamentoId());

        Pagamento entity = new Pagamento();
        entity.setData(LocalDateTime.now());
        entity.setTotal(pedido.getTotal());
        entity.setMetodoPagamento(metodo);

        entity.setPedido(pedido);
        pagamentoRepository.persist(entity);       
        return PagamentoDTOResponse.valueOf(entity);
    }

    @Override
    public List<PagamentoDTOResponse> findByUsuario(String email) {

        throw new UnsupportedOperationException("Unimplemented method 'findByUsuario'");
    }



}