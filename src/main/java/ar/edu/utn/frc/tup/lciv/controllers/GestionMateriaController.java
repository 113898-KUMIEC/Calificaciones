package ar.edu.utn.frc.tup.lciv.controllers;

import ar.edu.utn.frc.tup.lciv.DTOs.AlumnoMateriasResponse;
import ar.edu.utn.frc.tup.lciv.DTOs.InformeResponse;
import ar.edu.utn.frc.tup.lciv.DTOs.PutRequest;
import ar.edu.utn.frc.tup.lciv.DTOs.PutResponse;
import ar.edu.utn.frc.tup.lciv.service.GestionMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gestion")
public class GestionMateriaController {
    @Autowired
    private GestionMateriaService gestionMateriaService;
    @PostMapping
    public ResponseEntity<List<AlumnoMateriasResponse>> postMateria() throws IllegalAccessException {
        return new ResponseEntity<>(gestionMateriaService.postTodo(), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<PutResponse> putCalificacion(PutRequest putRequest) throws IllegalAccessException {
        return new ResponseEntity<>(gestionMateriaService.putCalificacion(putRequest), HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<InformeResponse>> getInforme(@RequestParam(required = false) String materia) throws IllegalAccessException {
        return new ResponseEntity<>(gestionMateriaService.getAllInformes(materia), HttpStatus.CREATED);
    }
}
