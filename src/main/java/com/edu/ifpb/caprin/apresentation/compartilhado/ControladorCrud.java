package com.edu.ifpb.caprin.apresentation.compartilhado;

import com.edu.ifpb.caprin.apresentation.compartilhado.resposta.Resposta;
import com.edu.ifpb.caprin.model.compartilhado.modelo.DominioModelo;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

import static org.springframework.http.HttpStatus.*;

public abstract class ControladorCrud <M extends DominioModelo<K>, K extends Serializable, I, O> {

    @GetMapping
    @ResponseStatus(OK)
    public abstract Page<O> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "linesPerPage", required = false, defaultValue = "10") int linesPerPage,
            @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction,
            @RequestParam(value = "orderBy", required = false, defaultValue = "id") String orderBy
    );

    @GetMapping(ApiEndpoints.ID)
    @ResponseStatus(OK)
    public abstract O findById(@PathVariable K id);

    @PostMapping
    @ResponseStatus(CREATED)
    public abstract O register(@RequestBody I request);

    @PutMapping(ApiEndpoints.ID)
    @ResponseStatus(OK)
    public abstract O update(@PathVariable K id, @RequestBody I request);

    @DeleteMapping(ApiEndpoints.ID)
    @ResponseStatus(NO_CONTENT)
    public abstract Resposta<?> delete(@PathVariable K id);


}
