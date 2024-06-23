package com.mindhub.homebanking.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;
import java.util.List;


//Cross Origin Resource Sharing(compartir recursos de origenes cruzados)
//caracteristica de seguridad que permite a los navegadores restringir el acceso a los recursos de un servidor
//permite a los servidores especificar no solo quien puede acceder a sus recursos, sino tambien como pueden ser accedidos

@Configuration //para que spring la tome como una clase de configuracion
public class CorsConfig {

  @Bean //para ponerla en contexto de spring
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080","http://localhost:5173", "https://homebankingg.onrender.com"));//rutas que pueden acceder a los recursos
    configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));//metodos que pueden acceder a los recursos
    configuration.setAllowedHeaders(List.of("*"));//lista de headers que se pueden usar en las peticiones
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);//registra la configuracion de cors para todas las rutas
    return source;
  }
}
