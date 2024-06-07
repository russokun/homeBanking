package com.mindhub.homebanking.configurations;

import com.mindhub.homebanking.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class WebConfig {

  @Autowired
  private JwtRequestFilter jwtRequestFilter;

  @Autowired
  private CorsConfigurationSource corsConfigurationSource;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
            .cors(cors -> cors.configurationSource(corsConfigurationSource)) //metemos la configuracion cors hecha
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)

            .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(
                    HeadersConfigurer.FrameOptionsConfig::disable))//para que se pueda acceder a la consola de h2

            .authorizeHttpRequests(authorize ->
                    authorize
                            .requestMatchers("/api/auth/login", "/api/auth/register", "/h2-console/**").permitAll()
                            .requestMatchers("/api/auth/current","/api/clients/current/accounts","/api/clients/current/cards").hasAnyRole("CLIENT")
                            .requestMatchers("/api/clients/{id}","/api/clients","/api/clients/accounts/{id}").hasRole("ADMIN")
                            .anyRequest().authenticated() //cualquier otra peticion requiere autenticacion
            )

            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)//agregamos el filtro antes de la autenticacion
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));//trabajamos sin sesiones, si no con tokens

    return httpSecurity.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() { //encriptacion de contraseñas
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception { //gestion de autenticacion
    return authenticationConfiguration.getAuthenticationManager();
  }
}