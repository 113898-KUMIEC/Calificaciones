package ar.edu.utn.frc.tup.lciv.DTOs;

import ar.edu.utn.frc.tup.lciv.Entity.MateriaEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoMateriasResponse {
    private String legajo;
    private String nombre;
    private List<MateriaResponse> materias;
}
