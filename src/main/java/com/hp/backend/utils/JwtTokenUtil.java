package com.hp.backend.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.hp.backend.entity.Account;
import com.hp.backend.model.TokenPayload;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
    private String secret = "Thang";

    public String generateToken(Account account, long expiredDate) {
        Map<String, Object> claims = new HashMap<>();

        TokenPayload tokenPayload = TokenPayload.builder().account_id(account.getAccount_id()).role(account.getRole())
                .build();
        claims.put("payload", tokenPayload);
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredDate * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // Invalid token
            return false;
        }
    }

    public TokenPayload decodeToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        TokenPayload payload = claims.get("payload", TokenPayload.class);
        return payload;
    }

    public TokenPayload getTokenPayload(String token) {
        return getClaimsFromToken(token, (Claims claim) -> {
            Map<String, Object> mapResult = (Map<String, Object>) claim.get("payload");
            return TokenPayload.builder()
                    .account_id((int) mapResult.get("account_id"))
                    .role((int) mapResult.get("role")).build();
        });
    }

    private <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    public boolean validate(String token, Account account) {
        TokenPayload tokenPayload = getTokenPayload(token);

        return tokenPayload.getAccount_id() == account.getAccount_id()
                && tokenPayload.getRole() == account.getRole()
                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiredDate = getClaimsFromToken(token, Claims::getExpiration);
        return expiredDate.before(new Date());
    }

    public String getRequestToken(HttpServletRequest request) {
        final String requestTokenHeader = request.getHeader("Authorization");
        String token = null;

        if(requestTokenHeader != null && requestTokenHeader.startsWith("Token ")){
            token = requestTokenHeader.substring(6).trim();
        }

        return token;
    }
}
