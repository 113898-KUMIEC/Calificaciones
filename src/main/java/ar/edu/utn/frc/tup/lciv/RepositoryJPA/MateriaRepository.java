package ar.edu.utn.frc.tup.lciv.RepositoryJPA;

import ar.edu.utn.frc.tup.lciv.Entity.MateriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MateriaRepository extends JpaRepository<MateriaEntity,Long> {
    List<MateriaEntity> findByMateria(String materia);
}
