package com.dh.integradora.service;

import com.dh.integradora.dominio.Odontologo;

import java.util.List;

public interface OdontologoServiceInterface {
    List<Odontologo> listarOdontologos();
    Odontologo buscar(int id);
    Odontologo guardar(Odontologo odontologo);
    Odontologo actualizar(Odontologo odontologo);
    void eliminar(int id);
}
