package com.edu.ifpb.caprin.model.compartilhado.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
@NoRepositoryBean
public interface DominioRepositorio<E, I extends Serializable> extends JpaRepository<E, I>, JpaSpecificationExecutor<E> {



}
