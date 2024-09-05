package ar.edu.utn.frc.tup.lciv.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformeResponse {
    private String materia;
    private Estado estado;
    private String resultado;
}
