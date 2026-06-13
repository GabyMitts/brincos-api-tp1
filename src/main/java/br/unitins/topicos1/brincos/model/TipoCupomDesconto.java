package br.unitins.topicos1.brincos.model;

public enum TipoCupomDesconto {

    ValorFixo (1l, "Valor Fixo"),
    Percentual (2l, "Percentual");

    public final Long ID;
    public final String LABEL;

    TipoCupomDesconto(Long id, String label) {
        this.ID = id;
        this.LABEL = label;
    }

    public static TipoCupomDesconto valueOf(Long id) {
        if (id == null)
            return null;
        
        for (TipoCupomDesconto tipo : TipoCupomDesconto.values())
            if (tipo.ID.equals(id))
                return tipo;
        
        throw new IllegalArgumentException("id inválido");
    }

}