package com.teste.selaz.selaz.security;

import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.Keys;

public class JwtUtils {

    private static final String KEY = "com.teste.selaz.selaz.projetoiasecuritytoken";

    public static String generateToken(Authentication authentication) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Login user = new Login();
        user.setEmail(authentication.getName());
        if (!authentication.getAuthorities().isEmpty()) {
            String nivelAcesso = authentication.getAuthorities().stream().toList().get(0).getAuthority();

            user.setNivel(nivelAcesso);
        }
        String jsonUser = mapper.writeValueAsString(user);
        Date now = new Date();
        Long hours = 1000L * 60L * 60L * 24; // Um dia
        return Jwts.builder()
            .claim("userDetails", jsonUser)
            .setIssuer("com.teste.selaz.selaz")
            .setSubject(authentication.getName())
            .setExpiration(new Date(now.getTime() + hours))
            .signWith(Keys.hmacShaKeyFor(KEY.getBytes()), SignatureAlgorithm.HS256).compact();
    }
  
    public static Authentication parseToken(String token)
        throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String credentialsJson = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes())).build()
            .parseClaimsJws(token).getBody().get("userDetails", String.class);
        Login user = mapper.readValue(credentialsJson, Login.class);

        UserDetails userDetails = User.builder().username(user.getEmail()).password("secret")
            .authorities(user.getNivel().toString()).build();
        return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(),
            userDetails.getAuthorities());
    }

}