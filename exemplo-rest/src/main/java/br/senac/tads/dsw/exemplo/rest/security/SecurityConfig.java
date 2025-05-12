package br.senac.tads.dsw.exemplo.rest.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
// @EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        BCryptPasswordEncoder bcryptEnc = new BCryptPasswordEncoder();
        encoders.put("bcrypt", bcryptEnc);
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        var passwordEncoder = new DelegatingPasswordEncoder("bcrypt", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(bcryptEnc);
        return passwordEncoder;
    }

    @Bean
    AuthenticationManager authenticationManager(
            UsuarioSistemaService usuarioService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authProvider);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // @formatter:off
        return http
                .cors(cors -> Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(fo -> fo.sameOrigin())) // Permite mostrar H2 Console
                .formLogin(formLogin -> formLogin.disable())
                // .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize
                        -> authorize
                        .requestMatchers("/login", "/login.html", "/me.html",
                                "/h2-console/**",
                                "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
//                       .requestMatchers("/peao").hasAuthority("SCOPE_PEAO")
//                       .requestMatchers("/gerente").hasAuthority("SCOPE_GERENTE")
//                       .requestMatchers("/diretor").hasAuthority("SCOPE_DIRETOR")
                        .anyRequest().authenticated())
//				// Adicionar filtro JWT ANTES do filtro padrão de Username/Password
//				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
        // @formatter:on
    }

}
