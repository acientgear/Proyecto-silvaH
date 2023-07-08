package com.app.silvahnosbe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.app.silvahnosbe.security.filters.JwtAuthenticationFilter;
import com.app.silvahnosbe.security.filters.JwtAuthorizationFilter;
import com.app.silvahnosbe.security.jwt.JwtUtils;
import com.app.silvahnosbe.services.UserDetailsServiceImpl;
import org.springframework.http.HttpMethod;
@Configuration
public class SecurityConfig {
    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception{

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);

        return http
            .csrf(config -> config.disable())
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/login","/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll();
                auth.requestMatchers("/iniciar_sesion").permitAll();
                auth.requestMatchers(HttpMethod.OPTIONS).permitAll();
                auth.requestMatchers("/**").authenticated();
                auth.anyRequest().authenticated();

            })
            .sessionManagement(session -> {
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            })
            .addFilter(jwtAuthenticationFilter)
            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    /* 
    @Bean
    UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("pass"))
                .roles()
                .build());
        return manager;
    }
*/

    @Bean
    PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }


    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception{

        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and().build();
    }


}
