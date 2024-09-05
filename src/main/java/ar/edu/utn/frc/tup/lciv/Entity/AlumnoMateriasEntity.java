package ar.edu.utn.frc.tup.lciv.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoMateriasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String legajo;
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MateriaEntity> materias;
}
