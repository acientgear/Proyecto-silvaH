package com.app.silvahnosbe.services;


import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.repositories.UsuarioRepository;

/**
 *servicios userDetails
 * @author Ignacio Grez
 */


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;


    /**
     *funcion que permite cargar los datos de un usuario segun su nombre
     * esta funcion es necesaria para el funcionamiento de login
     *
     * @param username
     * @return una entidad user
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        UsuarioEntity usuario = usuarioRepository.findByUsuario(username)
                                .orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado: " + username));


        Collection<? extends GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getNombre().name())))
                .collect(Collectors.toSet());

        return new User(usuario.getUsuario(),usuario.getContrasenna(),true,true, true,true,authorities);
    }
    
}
