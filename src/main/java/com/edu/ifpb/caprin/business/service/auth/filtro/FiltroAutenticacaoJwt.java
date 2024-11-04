package com.edu.ifpb.caprin.business.service.auth.filtro;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.edu.ifpb.caprin.business.service.auth.PermissaoServico;
import com.edu.ifpb.caprin.model.repository.conta.ContaRepository;
import com.edu.ifpb.caprin.model.utils.TokenJwtUtils;

import java.io.IOException;
import java.util.Objects;

@Component
@AllArgsConstructor
public class FiltroAutenticacaoJwt extends OncePerRequestFilter {

    private final TokenJwtUtils tokenJwtUtils;

    private final ContaRepository contaRepositorio;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String token = getToken(request);

        try {
            if (StringUtils.hasText(token) && tokenJwtUtils.validarToken(token)) {

                String email = tokenJwtUtils.getEmail(token);

                var conta = contaRepositorio.findByEmail(email).orElse(null);

                if (Objects.nonNull(conta)) {
                    var autenticacao = new UsernamePasswordAuthenticationToken(
                            conta.getEmail(),
                            conta.getSenha(),
                            PermissaoServico.converterParaAuthority(conta.getContaTipo())
                    );

                    autenticacao.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(autenticacao);
                    response.setHeader(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
                }
            }
        } finally {
            filterChain.doFilter(request, response);
        }

    }

    private String getToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }


}
