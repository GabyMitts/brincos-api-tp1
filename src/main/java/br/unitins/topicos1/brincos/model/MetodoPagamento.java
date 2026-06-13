package br.unitins.topicos1.brincos.model;

public enum MetodoPagamento {

    CartaoCredito (1l, "Cartao de Crédito"),
    CartaoDebito (2l, "Cartao de Débito"),
    Boleto (3l, "Boleto Bancário"),
    Pix (4l, "Pix");

    public final Long ID;
    public final String LABEL;

    MetodoPagamento(Long id, String label) {
        this.ID = id;
        this.LABEL = label;
    }

    public static MetodoPagamento valueOf(Long id) {
        if (id == null)
            return null;
        
        for (MetodoPagamento metodo : MetodoPagamento.values())
            if (metodo.ID.equals(id))
                return metodo;
        
        throw new IllegalArgumentException("id inválido");
    }

}