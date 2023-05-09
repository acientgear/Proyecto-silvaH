package com.app.silvahnosbe.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.entities.SaldoEntity;
import com.app.silvahnosbe.repositories.SaldoRepository;

@Service
public class SaldoService {
    @Autowired
    SaldoRepository saldoRepository;

    public ArrayList<SaldoEntity> obtenerSaldos(){
        return saldoRepository.obtenerSaldos();
    }
}
