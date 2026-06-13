package br.unitins.topicos1.brincos.service;

import java.util.Arrays;
import java.util.List;

import br.unitins.topicos1.brincos.dto.MetodoPagamentoDTOResponse;
import br.unitins.topicos1.brincos.model.MetodoPagamento;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MetodoPagamentoServiceImpl implements MetodoPagamentoService {

    @Override
    public List<MetodoPagamentoDTOResponse> getAll() {
        return Arrays.stream(MetodoPagamento.values())
                .map(MetodoPagamentoDTOResponse::valueOf)
                .toList();
    }

    @Override
    public MetodoPagamentoDTOResponse findById(Long id) {
        return MetodoPagamentoDTOResponse.valueOf(MetodoPagamento.valueOf(id));
    }


}