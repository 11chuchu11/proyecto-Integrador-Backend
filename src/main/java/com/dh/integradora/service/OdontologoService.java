package com.dh.integradora.service;

import com.dh.integradora.dao.IDao;
import com.dh.integradora.dominio.Odontologo;
import com.dh.integradora.service.OdontologoServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements OdontologoServiceInterface {

    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao){
        this.odontologoIDao = odontologoIDao;
    }

    @Override
    public List<Odontologo> listarOdontologos() {
        return odontologoIDao.listarElementos();
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return odontologoIDao.guardar(odontologo);
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        return odontologoIDao.actualizar(odontologo);
    }

    @Override
    public Odontologo buscar(int id) {
        return odontologoIDao.buscarId(id);
    }

    @Override
    public void eliminar(int id) {
        odontologoIDao.eliminar(id);
    }
}
