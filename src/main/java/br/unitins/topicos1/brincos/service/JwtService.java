package br.unitins.topicos1.brincos.service;

import br.unitins.topicos1.brincos.model.Perfil;

public interface JwtService {

    public String generateJwt(String usuario, Perfil perfil);
    
}