package com.app.silvahnosbe.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
    
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.time.expiration}")
    private String timeExpiration;

    public String generarTokenAcceso(String usermane){
        return Jwts.builder()
                .setSubject(usermane)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
                .signWith(obteberKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public boolean isValid(String token){

        try {
            Jwts.parserBuilder()
                .setSigningKey(obteberKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
                return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String obtenerNombreUsuario(String token){
        return  extraerParametroToken(token, Claims::getSubject);
    }

    public <T> T extraerParametroToken(String token, Function<Claims, T> claimsTFunction){

        Claims claims = extraerParametrosToken(token);
        return claimsTFunction.apply(claims);
    }

    public Claims extraerParametrosToken(String token){

        return Jwts.parserBuilder()
                .setSigningKey(obteberKey())
                .build()
                .parseClaimsJws(token)
                .getBody();        
    }


    public Key obteberKey(){

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
