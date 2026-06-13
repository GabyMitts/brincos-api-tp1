package br.unitins.topicos1.brincos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = Shape.OBJECT)
public enum ClassificacaoTamanho {
    PEQUENO(1, "Pequeno"), 
    MEDIO(2, "Médio"), 
    GRANDE(3, "Grande");

    @JsonProperty("id")
    public final Long ID;

    @JsonProperty("nome")
    public final String NOME;

    ClassificacaoTamanho(long id, String nome) {
        this.ID = id;
        this.NOME = nome;
    }

    public static ClassificacaoTamanho valueOf(Long id) {
        for (ClassificacaoTamanho classificacaoTamanho : ClassificacaoTamanho.values()) {
            if (id == classificacaoTamanho.ID)
                return classificacaoTamanho;
        }
        return null;
    }
}