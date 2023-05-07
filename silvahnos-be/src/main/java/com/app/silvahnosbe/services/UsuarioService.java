package com.app.silvahnosbe.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.UsuarioEntity;
import com.app.silvahnosbe.models.FlujoModel;
import com.app.silvahnosbe.entities.FlujoEntity;
import com.app.silvahnosbe.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    public ArrayList<UsuarioEntity> obtenerUsuarios(){
        return (ArrayList<UsuarioEntity>) usuarioRepository.findAll();

    }

    public UsuarioEntity guardarUsuario(UsuarioEntity usuario){
        return usuarioRepository.save(usuario);
    }
    public void borrar(Long id){
        usuarioRepository.deleteById(id);
    }

    public ArrayList<FlujoEntity> obtenerFlujo() {
        ArrayList<FlujoModel> flujo = (ArrayList<FlujoModel>) usuarioRepository.obtenerFlujo();
        ArrayList<FlujoEntity> flujoEntity = new ArrayList<FlujoEntity>();
        for(int i = 0; i < flujo.size(); i++){
            if(i==0){
                flujoEntity.add(new FlujoEntity(flujo.get(i).getAnio(), flujo.get(i).getMes(), flujo.get(i).getTotal_ingresos(), flujo.get(i).getTotal_egresos(), flujo.get(i).getTotal_ingresos()-flujo.get(i).getTotal_egresos()));
            }
            else{
                flujoEntity.add(new FlujoEntity(flujo.get(i).getAnio(), flujo.get(i).getMes(), flujo.get(i).getTotal_ingresos(), flujo.get(i).getTotal_egresos(), flujoEntity.get(i-1).getSaldo_cuenta()+flujo.get(i).getTotal_ingresos()-flujo.get(i).getTotal_egresos()));
            }
        }
        return flujoEntity;
    }
   
}
