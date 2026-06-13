package br.unitins.topicos1.brincos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Piercing extends Produto {
    
    @Enumerated(EnumType.STRING)
    private LocalAplicacao localAplicacao;

    public LocalAplicacao getLocalAplicacao() {
        return localAplicacao;
    }   
    public void setLocalAplicacao(LocalAplicacao localAplicacao) {
        this.localAplicacao = localAplicacao;
    }
}
