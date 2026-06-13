package br.unitins.topicos1.brincos.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class ListaDesejo extends DefaultEntity{
    
    @Column(unique = true)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "listaDesejo", cascade = CascadeType.PERSIST)
    private List<ItemListaDesejo> itensListaDesejo;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ItemListaDesejo> getItensListaDesejo() {
        return itensListaDesejo;
    }
    public void setItensListaDesejo(List<ItemListaDesejo> itensListaDesejo) {
        this.itensListaDesejo = itensListaDesejo;
    }

} 

