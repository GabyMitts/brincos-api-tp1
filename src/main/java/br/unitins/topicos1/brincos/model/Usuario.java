package br.unitins.topicos1.brincos.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;

@Entity
public class Usuario extends DefaultEntity {

    private String nome;
    @Column(unique = true)
    private @Email String email;
    private String senha;
    private String telefone;
    private String tokenRecuperacaoSenha;

    // @OneToMany(mappedBy = "usuario")
    // private List<EnderecoUsuario> enderecos;

    @ManyToOne
    @JoinColumn(name = "endereco_id", nullable = true)
    private EnderecoUsuario enderecos;

    private Perfil perfil;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Perfil getPerfil() {
        return perfil;
    }
    
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setTokenRecuperacaoSenha(String token) {
        this.tokenRecuperacaoSenha = token;
    }
    public String getTokenRecuperacaoSenha() {
        return tokenRecuperacaoSenha;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<EnderecoUsuario> getEnderecos() {
        return List.of(enderecos);
    }
    public void setEnderecos(List<EnderecoUsuario> enderecos) {
        this.enderecos = enderecos.get(0);
    }
}