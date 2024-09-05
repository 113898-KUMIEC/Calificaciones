package ar.edu.utn.frc.tup.lciv.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Docentes {
    private String matricula;
    private String nombre;
    private String materia;
}
