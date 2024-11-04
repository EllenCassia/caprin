package com.edu.ifpb.caprin.model.entity.Enum;

public enum ContaTipo {
    
    ADMIN("Admin", 0),
    ORGANIZADOR("Organizador", 1),
    EXPOSITOR("Expositor", 2);

    //   0         1
    // False      True

    private final String label;
    private final int posicao;

    ContaTipo(String label, int posicao) {
        this.label = label;
        this.posicao = posicao;
    }

    public int getPosicao() {
        return posicao;
    }

    public String getLabel() {
        return label;
    }

    public ContaTipo getValor() {
        return this;
    }

}