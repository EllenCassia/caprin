package com.edu.ifpb.caprin.model.compartilhado.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
public abstract class DominioModelo<I extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "dhcriacao", nullable = false)
    private LocalDateTime dhCriacao;

    public abstract I getId();

    public void iniciar() {};

    public void marcarDHcriacao() {
        setDhCriacao(LocalDateTime.now());
    }
    public void validar() throws RuntimeException {};
    
    public void registrar() {
        marcarDHcriacao();
        iniciar();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DominioModelo<I> other = (DominioModelo<I>) obj;
        return Objects.equals(getId(), other.getId()) && Objects.equals(getDhCriacao(), other.getDhCriacao());
    }



}
