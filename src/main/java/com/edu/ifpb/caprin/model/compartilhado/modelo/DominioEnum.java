package com.edu.ifpb.caprin.model.compartilhado.modelo;

public interface DominioEnum<E extends Enum<E>>{

    String getLabel();
    E getValor();
}
