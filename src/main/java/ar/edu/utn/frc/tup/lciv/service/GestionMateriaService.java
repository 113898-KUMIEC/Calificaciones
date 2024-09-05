package ar.edu.utn.frc.tup.lciv.service;

import ar.edu.utn.frc.tup.lciv.DTOs.AlumnoMateriasResponse;
import ar.edu.utn.frc.tup.lciv.DTOs.InformeResponse;
import ar.edu.utn.frc.tup.lciv.DTOs.PutRequest;
import ar.edu.utn.frc.tup.lciv.DTOs.PutResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GestionMateriaService {
    List<AlumnoMateriasResponse> postTodo();

    PutResponse putCalificacion(PutRequest putRequest);

    List<InformeResponse> getAllInformes(String materia);
}
