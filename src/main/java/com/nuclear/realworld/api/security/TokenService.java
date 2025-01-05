package com.nuclear.realworld.api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final AuthProperties properties;


    public String generateToken(String subject) {
        return buildToken(new HashMap<String, Object>(), subject);
    }

    public String generateToken(HashMap<String, Object> extraClaims, String subject) {
        return buildToken(extraClaims, subject);
    }

    private String buildToken(Map<String, Object> extraClaims, String subject) {
        var nowMillis = System.currentTimeMillis();

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(subject)
                .setIssuer("nuclear-realworld")
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(new Date(nowMillis + properties.getToken()
                        .getExpiration()))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    private Key getKey() {
        byte[] bytes = Decoders.BASE64.decode(properties.getToken()
                .getSecret());
        return Keys.hmacShaKeyFor(bytes);
    }

}
