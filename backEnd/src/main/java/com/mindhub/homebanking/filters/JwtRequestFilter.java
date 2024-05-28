package com.mindhub.homebanking.filters;

import com.mindhub.homebanking.servicesSecurity.JwtUtilService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

//Class para autenticar al usuario utilizando un token JWT que se pasa en el encabezado de la solicitud HTTP

@Component //marca la clase como un componente de Spring
public class JwtRequestFilter extends OncePerRequestFilter { //clase abstracta para filtrar las solicitudes HTTP (se ejecuta cada vez que se realiza una solicitud)

  @Autowired
  private UserDetailsService userDetailsService; //obtener datos de usuario

  @Autowired
  private JwtUtilService jwtUtilService; //para crear el token JWT
  //logica principal del filtro(peticion, respuesta y cadena de filtros[usamo alfinal para pasar soli y res a cadena de filtros])
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException { //excepcion general y de entrada y salida.
    final String authorizationHeader = request.getHeader("Authorization"); //encaezado de la solicitud HTTP. donde iba el valor de la APIKEY

    String username = null;//les declaramos datos una vez pasamos por la condicion
    String jwt = null;

    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      jwt = authorizationHeader.substring(7); //omitimos bearer y tomamos el token
      username = jwtUtilService.extractUserName(jwt); //extraemos el nombre de usuario del token
    }

    try {
      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username); //cargamos los datos del usuario
        if (!jwtUtilService.isTokenExpired(jwt)) { //verificamos si el token ha expirado
          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken //creamos un token de autenticacion
                  (userDetails, null, userDetails.getAuthorities());//null pq trabajamos a traves de token

          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));//detalles de la autenticacion basados en autenticacion proporcionada
          SecurityContextHolder.getContext().setAuthentication(authentication);//gestion authorizacion/authenticacion del usuario
        }
      }
    }
    catch(Exception e){ //en caso de cualquier error que lo atrape
      System.out.println(e.getMessage());
    }
    finally{
      filterChain.doFilter(request, response);//pasamos la solicitud y la respuesta a la sig cadena de filtros
    }
  }
}
