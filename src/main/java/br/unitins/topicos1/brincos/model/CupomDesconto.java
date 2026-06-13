package br.unitins.topicos1.brincos.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class CupomDesconto extends DefaultEntity {
    private String nome;
    private LocalDateTime datafinal;
    private Float valorDesconto;
    private TipoCupomDesconto tipoCupomDesconto;
    private String codigo;
    private Float valorMinimo;
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDatafinal() {
        return datafinal;
    }
    public void setDatafinal(LocalDateTime datafinal) {
        this.datafinal = datafinal;
    }

    public Float getValorDesconto() {
        return valorDesconto;
    }
    public void setValorDesconto(Float valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public TipoCupomDesconto getTipoCupomDesconto() {
        return tipoCupomDesconto;
    }
    public void setTipoCupomDesconto(TipoCupomDesconto tipoCupomDesconto) {
        this.tipoCupomDesconto = tipoCupomDesconto;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Float getValorMinimo() {
        return valorMinimo;
    }
    public void setValorMinimo(Float valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

}