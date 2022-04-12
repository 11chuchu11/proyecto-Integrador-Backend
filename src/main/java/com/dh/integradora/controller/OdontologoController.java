package com.dh.integradora.controller;

import com.dh.integradora.dominio.Odontologo;
import com.dh.integradora.dominio.Paciente;
import com.dh.integradora.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    /*private final OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService){
        this.odontologoService = odontologoService;
    }*/


    @PostMapping
    public Odontologo registrarOdontologo(@RequestBody Odontologo odontologo) {return odontologoService.guardar(odontologo);}

    @PutMapping
    public Odontologo actualizarPaciente(@RequestBody Odontologo odontologo){
        return odontologoService.actualizar(odontologo);
    }

    @GetMapping("/{id}")
    public Odontologo buscarOdontologo(@PathVariable int id){
        return odontologoService.buscar(id);
    }

    @DeleteMapping("/{id}")
    public String eliminarOdontologo(@PathVariable int id){
        String resp = "Error en el id ingresado";
        if (odontologoService.buscar(id) != null){
            odontologoService.eliminar(id);
            resp = "Odontologo con id " + id + " eliminado";
        }
        return resp;
    }

    @GetMapping
    public List<Odontologo> buscarOdontologos(){
        return odontologoService.listarOdontologos();
    }


}
