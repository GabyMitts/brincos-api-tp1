package br.unitins.topicos1.brincos.dto;

import br.unitins.topicos1.brincos.model.Arquivo;

public record ArquivoResponseDTO(
    Long id,
    String fid,
    String nomeOriginal,
    String mimeType,
    Long tamanhoBytes,
    String sha256
) {
    public static ArquivoResponseDTO valueOf(Arquivo arquivo) {
        return new ArquivoResponseDTO(
            arquivo.getId(),
            arquivo.getFid(),
            arquivo.getNomeOriginal(),
            arquivo.getMimeType(),
            arquivo.getTamanhoBytes(),
            arquivo.getSha256()
        );
    }
}