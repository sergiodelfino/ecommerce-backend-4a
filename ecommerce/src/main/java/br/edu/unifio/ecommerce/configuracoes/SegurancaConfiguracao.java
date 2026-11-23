package br.edu.unifio.ecommerce.configuracoes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SegurancaConfiguracao {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService usuarios(PasswordEncoder encoder) {
        return new InMemoryUserDetailsManager(
            User.withUsername("admin")
                .password(encoder.encode("Admin@123"))
                .roles("ADMIN")
                .build(),
            User.withUsername("aluno")
                .password(encoder.encode("Aluno@123"))
                .roles("ALUNO")
                .build()
        );
    }

    @Bean
    public SecurityFilterChain filtros(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/usuarios", "/usuarios/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/veiculos/**",
                    "/rotas/**", "/paradas/**", "/viagens/**",
                    "/reservas/**", "/presencas/**", "/avisos/**")
                    .hasAnyRole("ADMIN", "ALUNO")
                .requestMatchers("/veiculos/**", "/rotas/**",
                    "/paradas/**", "/viagens/**", "/reservas/**",
                    "/presencas/**", "/avisos/**")
                    .hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .build();
    }
}
