package br.unitins.topicos1.brincos.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Colecao extends DefaultEntity{
    
    @Column(unique = true)
    private String nome;
    @OneToMany(mappedBy = "colecao", cascade = CascadeType.PERSIST)
    private List<Produto> produtos;

    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public List<Produto> getProdutos(){
        return produtos;
    }
    public void setProdutos(List<Produto> produtos){
        this.produtos = produtos;
    }
}

