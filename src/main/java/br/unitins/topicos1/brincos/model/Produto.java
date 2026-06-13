package br.unitins.topicos1.brincos.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
abstract public class Produto extends DefaultEntity{
    
    private String nome;
    private Float preco;
    private Integer estoque;
    private Float tamanho;
    private ClassificacaoTamanho tamanhoClassificacao;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "produtos_materiais",
        joinColumns = @JoinColumn(name = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_material") 
    )
    public Set<Material> material = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_formato")
    private Formato formato;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "produtos_cores",
        joinColumns = @JoinColumn(name = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_cor") 
    )
    public Set<Cor> cor = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_colecao")
    private Colecao colecao;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "produto_arquivo", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "arquivo_id", unique = true))
    public List<Arquivo> arquivos = new ArrayList<>();

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPreco() {
        return preco;
    }
    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Float getTamanho() {
        return tamanho;
    } 
    public void setTamanho(Float tamanho) {
        this.tamanho = tamanho;
    }

    public Colecao getColecao() {
        return colecao;
    }
    public void setColecao(Colecao colecao) {
        this.colecao = colecao;
    }

    public Formato getFormato() {
        return formato;
    }
    public void setFormato(Formato formato) {
        this.formato = formato;
    }

    public ClassificacaoTamanho getTamanhoClassificacao() {
        return tamanhoClassificacao;
    }
    
    public List<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }

    public void addArquivo(Arquivo arquivo) {
        if (arquivo == null) {
            return;
        }
        arquivos.add(arquivo);
    }

    public void removeArquivo(Arquivo arquivo) {
        if (arquivo == null) {
            return;
        }
        arquivos.remove(arquivo);
    }
} 

