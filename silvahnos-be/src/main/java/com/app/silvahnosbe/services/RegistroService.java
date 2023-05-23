package com.app.silvahnosbe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.models.Registro;
import com.app.silvahnosbe.repositories.RegistroRepository;

@Service
public class RegistroService {
    @Autowired
    RegistroRepository registroRepository;

    public List<Registro> obtenerRegistros(int anio, int mes){
        return (List<Registro>) registroRepository.obtenerRegistros(anio, mes);
    }
}
