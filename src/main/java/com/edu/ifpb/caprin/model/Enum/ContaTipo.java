package com.edu.ifpb.caprin.model.Enum;

public enum ContaTipo {

    ADMIN("ADMIN"),
    ORGANIZADOR("ORGANIZADOR"),
    EXPOSITOR("EXPOSITOR");

    private final String tipo;

    ContaTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
    
}
