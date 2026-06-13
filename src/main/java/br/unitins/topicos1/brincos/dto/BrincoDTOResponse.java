package br.unitins.topicos1.brincos.dto;

import java.util.List;

import br.unitins.topicos1.brincos.model.Brinco;

public record BrincoDTOResponse(
    Long id,
    String nome,
    Float tamanho,
    Float preco,
    String nomeColecao,     
    Long idColecao,
    String nomeFormato,
    Long idFormato,
    List<CorDTOResponse> cores,
    List<MaterialDTOResponse> materiais,
    List<ArquivoResponseDTO> arquivos
) {
    public static BrincoDTOResponse valueOf(Brinco brinco){
        return new BrincoDTOResponse(
            brinco.getId(),
            brinco.getNome(),
            brinco.getTamanho(),
            brinco.getPreco(),
            brinco.getColecao() != null ? brinco.getColecao().getNome() : null,
            brinco.getColecao() != null ? brinco.getColecao().getId() : null,
            brinco.getFormato().getNome() != null ? brinco.getFormato().getNome() : null,
            brinco.getFormato().getId() != null ? brinco.getFormato().getId() : null,
            brinco.cor.stream().map(c -> CorDTOResponse.valueOf(c)).toList(),
            brinco.material.stream().map(m -> MaterialDTOResponse.valueOf(m)).toList(),
            brinco.getArquivos().stream().map(ArquivoResponseDTO::valueOf).toList()
        );
    }
}