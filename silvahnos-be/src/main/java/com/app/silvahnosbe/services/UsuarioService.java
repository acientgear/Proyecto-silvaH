package com.app.silvahnosbe.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.repositories.UsuarioRepository;

public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    public ArrayList<UsuarioEntity> obtenerUsuarios(){
        return (ArrayList<UsuarioEntity>) usuarioRepository.findAll();

    }

    public UsuarioEntity guardarUsuario(UsuarioEntity usuario){
        return usuarioRepository.save(usuario);
    }
}
