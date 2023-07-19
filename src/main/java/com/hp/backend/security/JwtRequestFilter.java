package com.hp.backend.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hp.backend.entity.Account;
import com.hp.backend.model.TokenPayload;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.utils.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;
    private final AccountRepository accountRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        String token = null;
        TokenPayload tokenPayload = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Token ")) {
            token = requestTokenHeader.substring(6).trim();
            tokenPayload = parseToken(token);
        } else {
            logger.error("JWT TOKEN does not start with Token");
        }

        if (tokenPayload != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<Account> accountOptional = getAccountFromTokenPayload(tokenPayload);

            if (accountOptional.isPresent()) {
                Account account = accountOptional.get();
                if (jwtTokenUtil.validate(token, account)) {
                    handleValidToken(account, tokenPayload);
                } else {
                    logger.error("Token khong hop le");
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private TokenPayload parseToken(String token) {
        TokenPayload tokenPayload = null;
        try {
            tokenPayload = jwtTokenUtil.getTokenPayload(token);
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature", e);
        } catch (IllegalArgumentException ex) {
            logger.error("Unable to get JWT", ex);
        } catch (ExpiredJwtException ex) {
            logger.error("Token has expired", ex);
        }
        return tokenPayload;
    }

    private Optional<Account> getAccountFromTokenPayload(TokenPayload tokenPayload) {
        return accountRepository.findById(tokenPayload.getAccount_id());
    }

    private void handleValidToken(Account account, TokenPayload tokenPayload) {
        int role = tokenPayload.getRole();
        String roleString = "ROLE_" + role;
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleString);

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                account.getEmail(),
                account.getPassword(),
                Collections.singleton(authority));

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

}
