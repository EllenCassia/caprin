package com.edu.ifpb.caprin.business.service.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.edu.ifpb.caprin.model.entity.Enum.ContaTipo;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class PermissaoServico {

    public static String adicionarPermissao(ContaTipo tipo, String contaTipo) {
        char[] permissoesArray = contaTipo.toCharArray();
        permissoesArray[tipo.getPosicao()] = '1';
        contaTipo = new String(permissoesArray);
        return contaTipo;
    }

    public static String removerPermissao(ContaTipo tipo, String contaTipo) {
        char[] permissoesArray = contaTipo.toCharArray();
        permissoesArray[tipo.getPosicao()] = '0';
        contaTipo = new String(permissoesArray);
        return contaTipo;
    }

    public static String obterCodigo(Set<ContaTipo> tiposConta, String contaTipo) {
        for (ContaTipo tipo: tiposConta)
            contaTipo = adicionarPermissao(tipo, contaTipo);
        return contaTipo;
    }

    public static Set<ContaTipo> obterPermissoes(String contaTipo) {
        ContaTipo[] tipos = ContaTipo.values();
        return Arrays.stream(tipos)
                .filter(tipo -> possuiPermissao(tipo, contaTipo))
                .collect(Collectors.toSet());
    }

    public static boolean possuiPermissao(ContaTipo tipo, String contaTipo) {
        return contaTipo.charAt(tipo.getPosicao()) == '1';
    }

    public static Set<GrantedAuthority> converterParaAuthority(String contaTipo) {
        Set<ContaTipo> tipos = obterPermissoes(contaTipo);
        return tipos.stream()
                .map(tipo -> new SimpleGrantedAuthority(tipo.name()))
                .collect(Collectors.toSet());
    }

}
