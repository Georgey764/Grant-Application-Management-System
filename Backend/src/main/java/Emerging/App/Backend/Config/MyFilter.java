package Emerging.App.Backend.Config;

import Emerging.App.Backend.JWT.JWTutil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class MyFilter extends OncePerRequestFilter {

    private UserDetailsService userDetailsService;

    @Autowired
    @Lazy
    public MyFilter(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = null;
        String jwt = null;
        Cookie[] cookie = request.getCookies();
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer")){
            try{
                jwt = header.substring(7);
                JWTutil instance = new JWTutil();
                username = instance.decodeJWT(jwt).getPayload().getSubject();
            } catch (ExpiredJwtException e){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session Expired");
            }

        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails user = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authencation = new UsernamePasswordAuthenticationToken(user.getUsername(), "", user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authencation);
        }
        filterChain.doFilter(request, response);
    }
}
