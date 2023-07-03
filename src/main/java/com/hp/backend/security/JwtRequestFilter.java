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

            try {
                tokenPayload = jwtTokenUtil.getTokenPayload(token);

            } catch (SignatureException e) {
                System.out.println("Invalid JWT signature");
            } catch (IllegalArgumentException ex) {
                System.out.println("Unable to get JWT");
            } catch (ExpiredJwtException ex) {
                System.out.println("Token has expired");
            }
        } else {
            System.out.println("JWT TOKEN doest not start with Token");
        }

        if (tokenPayload != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<Account> accountOptional = accountRepository.findById(tokenPayload.getAccount_id());

            if (accountOptional.isPresent()) {
                Account account = accountOptional.get();
                if (jwtTokenUtil.validate(token, account)) {
                    // Extract role from the token payload
                    int role = tokenPayload.getRole();

                    // Create the authority for the role
                    String roleString = "ROLE_" + role;
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleString);

                    // Create the UserDetails object with the necessary information
                    UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                            account.getEmail(),
                            account.getPassword(),
                            Collections.singleton(authority));

                    // Create the authentication token and set it to the SecurityContextHolder
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    System.out.println("Token khong hop le");
                }
            }
        }

        filterChain.doFilter(request, response);

    }

}
