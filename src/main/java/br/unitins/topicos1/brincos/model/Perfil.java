package br.unitins.topicos1.brincos.model;

public enum Perfil {

    Admin (1l, "Administrador"),
    User (2l, "Usuário"),
    Client (3l, "Cliente");

    public final Long ID;
    public final String LABEL;

    Perfil(Long id, String label) {
        this.ID = id;
        this.LABEL = label;
    }

    public static Perfil valueOf(Long id) {
        if (id == null)
            return null;
        
        for (Perfil perfil : Perfil.values())
            if (perfil.ID.equals(id))
                return perfil;
        
        throw new IllegalArgumentException("id inválido");
    }

}