package br.unitins.topicos1.brincos.dto;

public class LoginDTOResponse {

    private String email;
    private String token;
    private String perfil;
    private String nome;
    private String expireTime;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerfil() {
        return perfil;
    }
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getExpireTime() {
        return expireTime;
    }
    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }
}