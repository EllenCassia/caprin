package com.edu.ifpb.caprin.model.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import com.edu.ifpb.caprin.business.service.exception.TokenException;
import com.edu.ifpb.caprin.model.dto.token.TokenResposta;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class TokenJwtUtils {

    public TokenResposta gerarToken(Authentication auth) {
        Instant agora = Instant.now();
        Instant momentoExpiracao = agora.plus(ConstantesUtils.EXPIRACAO_TOKEN_JWT_HORAS, ChronoUnit.HOURS);

        String token = Jwts.builder()
                            .setSubject("CAPRIN")
                            .claim("email", auth.getName())
                            .claim("permissões", popularPermissoes(auth.getAuthorities()))
                            .setIssuedAt(Date.from(agora))
                            .setExpiration(Date.from(momentoExpiracao))
                            .signWith(key())
                            .compact();

        return TokenResposta.builder()
                .tokenAcesso(token)
                .dataCriacao(Date.from(agora))
                .dataExpiracao(Date.from(momentoExpiracao))
                .build();
    }

    private SecretKey key() {
        String chave = "9I!QWg8t0cO^%ZVNTWKh6ZU71D4Gim9!kArfn5w4XE7OhKWKx^IuJ4RmFZ7Bgn$c*#nIks@7Eb&WOOWB";
        return Keys.hmacShaKeyFor(chave.getBytes(StandardCharsets.UTF_8));
    }

    public String getExpiracao(String token) {
        try {
            var claims = Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return String.valueOf(claims.getExpiration().toInstant());
        } catch (Exception e) {
            throw new TokenException(TokenJwtUtils.class + " TOKEN EXPIRADO token="+token);
        }
    }

    public String getEmail(String token) {
        try {
            var claims = Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return String.valueOf(claims.get("email"));
        } catch (Exception e) {
            throw new TokenException(TokenJwtUtils.class + " TOKEN INVÁLIDO token="+token);
        }
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (Exception ex) {
            throw new TokenException(TokenJwtUtils.class + " TOKEN INVÁLIDO token="+token);
        }
    }

    private String popularPermissoes(Collection<? extends GrantedAuthority> collection) {
        Set<String> set = new HashSet<>();
        collection.forEach(authority -> set.add(authority.getAuthority()));
        return String.join(",", set);
    }

}
