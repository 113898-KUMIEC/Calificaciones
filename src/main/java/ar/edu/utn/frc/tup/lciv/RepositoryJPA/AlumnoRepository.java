package ar.edu.utn.frc.tup.lciv.RepositoryJPA;

import ar.edu.utn.frc.tup.lciv.Entity.AlumnoMateriasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<AlumnoMateriasEntity,Long> {
    AlumnoMateriasEntity findByLegajo(String legajo);
}
