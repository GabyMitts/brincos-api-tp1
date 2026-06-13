package br.unitins.topicos1.brincos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemListaDesejo extends DefaultEntity {

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
    private Float preco;

    @ManyToOne
    @JoinColumn(name = "lista_desejo_id", nullable = false)
    private ListaDesejo listaDesejo;

    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Float getPreco() {
        return preco;
    }
    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public ListaDesejo getListaDesejo() {
        return listaDesejo;
    }
    public void setListaDesejo(ListaDesejo listaDesejo) {
        this.listaDesejo = listaDesejo;
    }
}