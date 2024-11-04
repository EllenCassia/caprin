package com.edu.ifpb.caprin.business.service.auth;

import java.util.Set;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.edu.ifpb.caprin.model.repository.conta.ContaRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AutenticadorService implements AuthenticationProvider{

    private final ContaRepository contaRepositorio;

    private final PasswordEncoder encoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        var conta = contaRepositorio
        .findByEmail(email)
        .orElseThrow(() -> new BadCredentialsException("Nenhuma conta registrada com essas credenciais!"));
        
        if (!conta.isAtiva() || !encoder.matches(password, conta.getSenha()))
        throw new BadCredentialsException("Credenciais inválidas!");
        
        Set<GrantedAuthority> authorities = PermissaoServico.converterParaAuthority(conta.getContaTipo());
        var autenticado = new UsernamePasswordAuthenticationToken(email, password, authorities);
        SecurityContextHolder.getContext().setAuthentication(autenticado);
        return autenticado;
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
    
}
