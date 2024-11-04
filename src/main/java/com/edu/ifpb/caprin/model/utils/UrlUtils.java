package com.edu.ifpb.caprin.model.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

@Component
public class UrlUtils {

    private final SecureRandom secureRandom;
    private final int tamanhoEmBytes;
    private final int tamanhoCodigo;
    private final String urlPadraoAtivacaoConta;

    public UrlUtils() {
        tamanhoCodigo = 4;
        secureRandom = new SecureRandom();
        tamanhoEmBytes = 128;
        String urlBase = "http://localhost:8080";
        urlPadraoAtivacaoConta = urlBase + "/api/caprin/ativar-conta?token=";
    }

    public String gerarToken() {
        byte[] tokenBytes = new byte[tamanhoEmBytes];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }

    public String gerarCodigoVerificacao() {
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        String codigo = String.valueOf(random.nextInt((int) Math.pow(10, 4)));
        while (codigo.length() != tamanhoCodigo)
            codigo = String.valueOf(random.nextInt((int) Math.pow(10, 4)));
        return codigo;
    }

    public String gerarUrlAtivacaoConta(String token) {
        return urlPadraoAtivacaoConta+token;
    }

}