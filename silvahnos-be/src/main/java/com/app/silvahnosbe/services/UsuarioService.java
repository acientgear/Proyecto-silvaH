package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    public List<UsuarioEntity> obtenerUsuarios(){
        return (List<UsuarioEntity>) usuarioRepository.findAll();

    }

    public UsuarioEntity guardarUsuario(UsuarioEntity usuario){
        return usuarioRepository.save(usuario);
    }
    public void borrar(Long id){
        usuarioRepository.deleteById(id);
    }
   
}
