package com.edu.ifpb.caprin.business.service.compartilhado.servico;

import com.edu.ifpb.caprin.business.service.exception.NoSuchElementFoundException;
import com.edu.ifpb.caprin.model.compartilhado.modelo.DominioModelo;
import com.edu.ifpb.caprin.model.compartilhado.repositorio.DominioRepositorio;
import com.edu.ifpb.caprin.model.utils.BeanUtils;
import com.edu.ifpb.caprin.model.utils.CollectionUtils;
import com.edu.ifpb.caprin.model.utils.ObjectUtils;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface DominioServico<M extends DominioModelo<I>, I extends Serializable>{


    DominioRepositorio <M, I> getRepositorio();

    Class<M> getDominioClasse();


    default M buscarPorID(I id) {
        Optional<M> entidade = getRepositorio().findById(id);

        return entidade.orElseThrow(()-> new NoSuchElementFoundException(getDominioClasse() + "NÃO ENCONTRADO id="+ id));
    }

    default List<M> findAllById(Collection<I> ids) {
        if (CollectionUtils.isEmpty(ids)) return CollectionUtils.emptyList();
        List<M> entities = getRepositorio().findAllById(ids);

        if (CollectionUtils.notEmpty(entities)) return entities;

        throw new NoSuchElementFoundException(getDominioClasse()+"NÃO ENCONTRADO id(s)"+ids);
    }
    
    default List<M> findAllById(Iterable<I> ids) {
        if (CollectionUtils.isEmpty(ids)) return CollectionUtils.emptyList();
        List<M> entities = getRepositorio().findAllById(ids);

        if (CollectionUtils.notEmpty(entities)) return entities;

        throw new NoSuchElementFoundException(getDominioClasse()+"NÃO ENCONTRADO id(s)"+ ids);
    }

    default M findByProperty(final String property, final Object value) {
        Optional<M> entity = getRepositorio()
                .findOne(Specification.where((root, query, builder) -> builder.equal(root.get(property), value)));

        return entity.orElseThrow(()->new NoSuchElementFoundException(getDominioClasse()+"NÃO ENCONTRADO "+ property+":"+value));
    }

    default List<M> findAllByProperty(final String property, final Collection<? extends Object> value) {
        if (CollectionUtils.isEmpty(value)) return CollectionUtils.emptyList();

        List<M> entities = getRepositorio()
                .findAll(Specification.where((root, query, builder) -> builder.equal(root.get(property), value)));

        if (entities.isEmpty()) throw new NoSuchElementFoundException(getDominioClasse()+"NÃO ENCONTRADO "+ property+":"+value);

        return entities;
    }

    default Page<M> findAll(Pageable pageable) {
        return getRepositorio().findAll(pageable);
    }

    default List<M> findAll(Specification<M> specification) {
        return getRepositorio().findAll(specification);
    }

    default List<M> findAll() {
        return getRepositorio().findAll();
    }


    default M save(M entity) {
        validate(entity);
        return getRepositorio().save(entity);
    }
    default M saveAndFlush(M entity) {
        validate(entity);
        return getRepositorio().saveAndFlush(entity);
    }
    default List<M> saveAll(Collection<M> entities) {
        validateAll(entities);
        return getRepositorio().saveAll(entities);
    }

    default M register(M entity) {
        entity.registrar();
        return save(entity);
    }
    default List<M> registerAll(Collection<M> entities) {
        return CollectionUtils.map(entities, this::register, Collectors.toList());
    }

    default M update(I id, M entity) {
        M saved = buscarPorID(id);
        M updated = BeanUtils.copyProperties(entity, saved);
        return save(updated);
    }

    default void validate(M entity) {
        if (ObjectUtils.isNull(entity)) {
            throw new RuntimeException(getDominioClasse()+"");
        }
        entity.validar();
    }
    default void validateAll(Collection<M> entities) {
        CollectionUtils.forEach(entities, this::validate);
    }

    default void deleteById(I id) {
        M entity = buscarPorID(id);
        getRepositorio().delete(entity);
    }

    default void deleteAllById(Collection<I> ids) {
        getRepositorio().deleteAllById(ids);
    }

}
