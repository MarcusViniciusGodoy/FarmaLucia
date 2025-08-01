package com.farmalucia.FarmaLucia.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class ConfiguracoesSeguranca {

    @Bean
    public UserDetailsService dadosUsuariosCadastrados() {
        UserDetails usuario1 = User.builder()
                .username("joao@email.com")
                .password("{noop}joao123")
                .roles("USER")
                .build();

        UserDetails usuario2 = User.builder()
                .username("maria@email.com")
                .password("{noop}maria123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(usuario1, usuario2);
    }

    @Bean
    public SecurityFilterChain filtrosSeguranca(HttpSecurity http) throws Exception {
        return http
                .cors().and()
                .csrf().disable()
                .headers(headers -> headers.cacheControl())  // já configura Cache-Control
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/logout", "/css/**", "/js/**", "/assets/**", "/api/verifica-autenticacao")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .formLogin(form -> form.loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll())
                .rememberMe(rememberMe -> rememberMe.key("lembarDeMim")
                .alwaysRemember(true))
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("http://127.0.0.1:5501", "http://localhost:5501"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}

