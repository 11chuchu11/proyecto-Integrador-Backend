package com.dh.integradora.controller;

import com.dh.integradora.dao.OdontologoDAOH2;
import com.dh.integradora.dao.PacienteDAOH2;
import com.dh.integradora.dao.TurnoLista;
import com.dh.integradora.dominio.Odontologo;
import com.dh.integradora.dominio.Paciente;
import com.dh.integradora.dominio.Turno;
import com.dh.integradora.service.OdontologoService;
import com.dh.integradora.service.PacienteService;
import com.dh.integradora.service.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private PacienteService pacienteService = new PacienteService(new PacienteDAOH2());
    private OdontologoService odontologoService = new OdontologoService(new OdontologoDAOH2());
    private TurnoService turnoService=new TurnoService(new TurnoLista());
    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos(){
        return ResponseEntity.ok(turnoService.listTurno());
    }

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno){
        ResponseEntity<Turno> respuesta;
        Paciente pacienteBus = pacienteService.buscar(turno.getPaciente().getId());
        Odontologo odontologoBus = odontologoService.buscar(turno.getOdontologo().getId());
        if(pacienteBus != null && odontologoBus !=null){
            respuesta = ResponseEntity.ok(turnoService.registrarTurno(turno));
        }else {
            respuesta=ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id){
        ResponseEntity<String> response;
        if(turnoService.buscar(id) != null){
            turnoService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.OK).body("Turno con id="+id+" ha sido eliminado");
        }else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro un turno con el id="+id);
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<Turno> actualizarOdontolog(@RequestBody Turno turno ){
        ResponseEntity<Turno> response;

        if (turnoService.actualizar(turno) != null){
            response = ResponseEntity.ok(turno);
        }else {
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }
}
