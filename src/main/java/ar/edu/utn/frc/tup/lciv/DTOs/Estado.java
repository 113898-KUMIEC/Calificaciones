package ar.edu.utn.frc.tup.lciv.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estado {
    private String libre;
    private String regular;
    private String promocionado;
}
