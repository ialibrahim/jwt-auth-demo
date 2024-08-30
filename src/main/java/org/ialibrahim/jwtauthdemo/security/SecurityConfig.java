package org.ialibrahim.jwtauthdemo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsUtils;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService customerUserDetailsService;
    private final JwtAuthFilter jwtAuthFilter;
    @Bean
    public Pbkdf2PasswordEncoder passwordEncoder() {

        Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm algorithm = Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA1;

        return new Pbkdf2PasswordEncoder("secret, encrypt me ;)", 16, 128, algorithm);
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       Pbkdf2PasswordEncoder passwordEncoder)
            throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customerUserDetailsService).passwordEncoder(passwordEncoder);
        authenticationManagerBuilder.parentAuthenticationManager(null);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(r -> r.requestMatchers(new AntPathRequestMatcher("**")).authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterAfter(jwtAuthFilter, BasicAuthenticationFilter.class);

        return http.build();
    }

}
