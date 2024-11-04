package com.edu.ifpb.caprin.business.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.edu.ifpb.caprin.business.service.auth.JwtAuthenticationEntryPoint;
import com.edu.ifpb.caprin.business.service.auth.filtro.FiltroAutenticacaoJwt;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class ConfiguracaoWeb {

    private final FiltroAutenticacaoJwt filtroAutenticacaoJwt;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(handling -> handling.authenticationEntryPoint(jwtAuthenticationEntryPoint));

        http
                .cors(cors -> cors.configurationSource(request -> {
                            var config = new CorsConfiguration();
                            config.setAllowedOrigins(Collections.singletonList("*"));
                            config.setAllowedMethods(
                                    List.of(
                                            HttpMethod.GET.name(),
                                            HttpMethod.POST.name(),
                                            HttpMethod.PUT.name(),
                                            HttpMethod.DELETE.name()
                                    )
                            );
                            config.setAllowCredentials(true);
                            config.setAllowedHeaders(Collections.singletonList("*"));
                            config.setExposedHeaders(List.of(HttpHeaders.AUTHORIZATION));
                            config.setMaxAge(3600L);
                            return config;
                        })
                );

        http.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(filtroAutenticacaoJwt, UsernamePasswordAuthenticationFilter.class);

        http.authorizeHttpRequests(req -> req
                .requestMatchers(
                        HttpMethod.POST,
                        "/conta", "/auth/login", "/auth/validar-token**").permitAll()
                .requestMatchers(
                        HttpMethod.GET,
                        "/caprin/ativar-conta**").permitAll()
                .requestMatchers(
                        "/v3/api-docs/**", 
                        "/swagger-ui/**", 
                        "/swagger-ui.html"
                ).permitAll()
                .requestMatchers(HttpMethod.POST, "/endereco").permitAll()
                .anyRequest().authenticated());

        http.
                logout((logout) -> {
                    logout.logoutUrl("/auth/logout");
                    logout.clearAuthentication(true);
                    logout.invalidateHttpSession(true);
                    logout.logoutSuccessHandler(
                            (request, response, authentication) -> SecurityContextHolder.clearContext()
                    );
                });

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
