package com.app.silvahnosbe.security.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.silvahnosbe.security.jwt.JwtUtils;
import com.app.silvahnosbe.services.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter{

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")){
             String trueToken = tokenHeader.substring(7);
             if(jwtUtils.isValid(trueToken)){
                String usuario = jwtUtils.obtenerNombreUsuario(trueToken);
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(usuario);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario,null, userDetails.getAuthorities());

                SecurityContextHolder .getContext().setAuthentication(authenticationToken);

             }
        }
        filterChain.doFilter(request, response);

    }
    
}
