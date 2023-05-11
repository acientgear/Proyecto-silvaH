package com.app.silvahnosbe.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.silvahnosbe.models.SaldoModel;
import com.app.silvahnosbe.repositories.SaldoRepository;

@Service
public class SaldoService {
    @Autowired
    SaldoRepository saldoRepository;

    public ArrayList<SaldoModel> obtenerSaldos(Integer anio){
        return saldoRepository.obtenerSaldos(anio);
    }

    public Integer obtenerSaldoCuenta(Integer anio, Integer mes){
        return saldoRepository.obtenerSaldoCuenta(anio, mes);
    }
}
