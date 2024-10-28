package com.edu.ifpb.caprin.model.entity.Enum;

public enum ContaTipo {

    ADMIN("ADMIN", 0),
    ORGANIZADOR("ORGANIZADOR", 1),
    EXPOSITOR("EXPOSITOR", 2);

    private String label;
    private int posicao;

    ContaTipo(String label, int posicao) {
        this.label = label;
        this.posicao = posicao;
    }

    public String getLabel() {
        return label;
    }

    public int getPosicao() {
        return posicao;
    }
}
