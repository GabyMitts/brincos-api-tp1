package br.unitins.topicos1.brincos.service;

public record ArquivoDownload(
    byte[] content,
    String contentType,
    String fileName
) {
}