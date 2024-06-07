package com.mindhub.homebanking.servicesSecurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// class para implementar un JWT a usuario que entra en mi app
@Service
public class JwtUtilService {

  private static final SecretKey SECRET_KEY = Jwts //clase parte de libreria jwt para trabajar con tokens
          .SIG //es para firmar el token jwtw
          .HS256 //algoritmo de encriptacion o de firma para el token (hash-based message authentication code)
          .key() //metodo que indica la clave que se usa para firmar el token
          .build(); // para finalizar construccion del obj y devolver el jwt con clave proporcionada

  private static final long EXPIRATION_TIME = 1000 * 60 * 60; //tiempo de expiracion del token (1h)

  public Claims extractAllClaims(String token) {//metodo para verificar un token con clave secreta, extrae y devuelve claims del token(payload)
    return  Jwts.parser()
            .verifyWith(SECRET_KEY)//arriba se crea objeto jwt y se analiza y verifica con la clave secreta
            .build() //para finalizar configuracion de verificacion y construir el objeto jwtParser para analizar tokens
            .parseSignedClaims(token)//metodo para analizar token firmado y devolver objeto signedJwt contiene Claims firmadas
            .getPayload();//devuelve las claims del usuario
  }
//t generico para que pueda devolver cualquier tipo de dato
  public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {//metodo para extraer una claim del token
    final Claims claims = extractAllClaims(token);//extrae todas las claims del token
    return claimsTFunction.apply(claims);//devuelve la claim del token
  }

  public String extractUserName(String token) {//metodo para extraer el nombre de usuario del token
    return extractClaim(token, Claims::getSubject);//devuelve el nombre de usuario del token
  }

  public Date extractExpiration(String token) {//metodo para extraer la fecha de expiracion del token
    return extractClaim(token, Claims::getExpiration);//devuelve la fecha de expiracion del token
  }

  public Boolean isTokenExpired(String token) {//metodo para verificar si el token ha expirado
    return extractExpiration(token).before(new Date());//devuelve true si la fecha de expiracion del token es antes de la fecha actual
  }

  public String generateToken(UserDetails userDetails){
    Map<String,Object> claims = new HashMap<>();//crea un mapa de claims
    String rol = userDetails.getAuthorities() //obtiene las autoridades del usuario
            .iterator()//convertir coleccion de autoridades en un iterador para recorrerla 1x1
            .next()//devuelve el siguiente elemento del iterador(para que tenga solo 1 rol)
            .getAuthority(); //obtiene el rol del usuario
    claims.put("role", rol);//agrega el rol al mapa de claims
    return createToken(claims, userDetails.getUsername());//devuelve el token con las claims y el nombre de usuario
  }

  public String createToken(Map<String, Object> claims, String username) {//metodo para crear un token
    return Jwts
            .builder()//inicia un objeto jwtBuilder
            .claims(claims)//agrega las claims al token que le pasamos x parametro
            .subject(username)//agrega el nombre de usuario al token (subject de payload)
            .issuedAt(new Date(System.currentTimeMillis()))//agrega la fecha de emision del token
            .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))//agrega la fecha de expiracion del token
            .signWith(SECRET_KEY)//firma el token con la clave secreta
            .compact();//construir el tokenJwt completo firmado devuelto como string
  }
}
