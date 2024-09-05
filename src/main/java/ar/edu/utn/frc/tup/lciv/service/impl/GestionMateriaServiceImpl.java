package ar.edu.utn.frc.tup.lciv.service.impl;

import ar.edu.utn.frc.tup.lciv.Client.RestClient;
import ar.edu.utn.frc.tup.lciv.DTOs.*;
import ar.edu.utn.frc.tup.lciv.Entity.AlumnoMateriasEntity;
import ar.edu.utn.frc.tup.lciv.Entity.MateriaEntity;
import ar.edu.utn.frc.tup.lciv.Model.Alumnos;
import ar.edu.utn.frc.tup.lciv.Model.Docentes;
import ar.edu.utn.frc.tup.lciv.RepositoryJPA.AlumnoRepository;
import ar.edu.utn.frc.tup.lciv.RepositoryJPA.MateriaRepository;
import ar.edu.utn.frc.tup.lciv.service.GestionMateriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GestionMateriaServiceImpl implements GestionMateriaService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RestClient restClient;
    @Autowired
    private AlumnoRepository alumnoRepository;
    @Autowired
    private MateriaRepository materiaRepository;

    public List<Alumnos> getAllAlumnos(){
        return restClient.getAllAlumnos().getBody();
    }
    public List<Docentes> getAllDocentes(){
        return restClient.getAllDocentes().getBody();
    }

    @Override
    public List<AlumnoMateriasResponse> postTodo() {
        List<Alumnos> alumnos = getAllAlumnos();
        List<Docentes> docentes = getAllDocentes();
        List<AlumnoMateriasResponse> alumnoMateriasResponses = new ArrayList<>();

        for (Alumnos alumno : alumnos) {
            // Crear la entidad AlumnoMateriasEntity
            AlumnoMateriasEntity alumnoMateriasEntity = new AlumnoMateriasEntity();
            alumnoMateriasEntity.setLegajo(alumno.getLegajo());
            alumnoMateriasEntity.setNombre(alumno.getNombre());

            List<MateriaEntity> materiaEntities = new ArrayList<>();

            for (Docentes docente : docentes) {
                MateriaEntity materiaEntity =new MateriaEntity();
                materiaEntity.setMateria(docente.getMateria());
                materiaEntity.setDocente(docente.getNombre());
                materiaEntity.setCalificacion(0L); // Calificación inicial
                materiaEntity.setEstado("Pendiente"); // Estado inicial

                // Agregar la entidad a la lista de materias del alumno
                materiaEntities.add(materiaEntity);
            }

            // Setear la lista de materias en el alumno
            alumnoMateriasEntity.setMaterias(materiaEntities);

            // Guardar la entidad en la base de datos
            alumnoRepository.save(alumnoMateriasEntity);

            // Mapear la entidad AlumnoMateriasEntity al DTO AlumnoMateriasResponse usando ModelMapper
            AlumnoMateriasResponse alumnoMateriasResponse = modelMapper.map(alumnoMateriasEntity, AlumnoMateriasResponse.class);

            // Agregar el DTO a la lista de respuestas
            alumnoMateriasResponses.add(alumnoMateriasResponse);
        }

        return alumnoMateriasResponses;
    }

    @Override
    public PutResponse putCalificacion(PutRequest putRequest){
        String estado = "";
        if (putRequest.getCalificacion() < 4){
            estado = "LIBRE";
        } else if (putRequest.getCalificacion() < 9) {
            estado = "REGULAR";
        }else {
            estado = "PROMOCIONADO";
        }
        AlumnoMateriasEntity alumnoMateriasEntity = alumnoRepository.findByLegajo(putRequest.getLegajo());
        for (MateriaEntity m:alumnoMateriasEntity.getMaterias()){
            if (m.getMateria().equals(putRequest.getMateria())){
                m.setCalificacion(putRequest.getCalificacion());
                m.setEstado(estado);
            }
        }
        AlumnoMateriasEntity updateAlumno =alumnoRepository.save(alumnoMateriasEntity);
        PutResponse putResponse = new PutResponse();
        putResponse.setMateria(putRequest.getMateria());
        putResponse.setLegajo(putRequest.getLegajo());
        putResponse.setEstado(estado);
        return putResponse;
    }

    @Override
    public List<InformeResponse> getAllInformes(String materia){
        List<MateriaEntity> materiaEntities;
        List<String> materias = new ArrayList<>();
        if (materia != null){
            materiaEntities = materiaRepository.findByMateria(materia);
            materias.add(materia);
        }else {
            materiaEntities = materiaRepository.findAll();
            materias = getMaterias();
        }

        List<InformeResponse> informeResponseList = new ArrayList<>();
        for (String s:materias){
            InformeResponse informeResponse = new InformeResponse();
            Estado estado = new Estado();
            int contadorLibre=0;
            int contadorRegular=0;
            int contadorPromocion=0;
            for (MateriaEntity m: materiaEntities){
                if (m.getMateria().equals(s)){
                    if (m.getCalificacion()< 4){
                        contadorLibre++;
                    } else if (m.getCalificacion()<9) {
                        contadorRegular++;
                    }else {
                        contadorPromocion++;
                    }
                    estado.setLibre(contadorLibre*100/calacularTotalMaterias(materiaEntities,s) + "%");
                    estado.setRegular(contadorRegular*100/calacularTotalMaterias(materiaEntities,s) + "%");
                    estado.setPromocionado(contadorPromocion*100/calacularTotalMaterias(materiaEntities,s) + "%");
                }
            }

            informeResponse.setMateria(s);
            informeResponse.setEstado(estado);
            informeResponse.setResultado(calcularResulado(estado));
            informeResponseList.add(informeResponse);
        }
        return informeResponseList;
    }

    private int calacularTotalMaterias(List<MateriaEntity> materiaEntities, String s) {
        int contador=0;
        for (MateriaEntity m: materiaEntities){
            if (m.getMateria().equals(s)){
                contador++;
            }
        }
        return contador;
    }

    private String calcularResulado(Estado estado) {
        double promocionado=Double.parseDouble(estado.getPromocionado().replace("%",""));
        double regular=Double.parseDouble(estado.getRegular().replace("%",""));

        if (promocionado+regular>60){
            return "EXITOSA";
        }else {
            return "FRACASO";
        }
    }

    public List<String> getMaterias(){
        List<String> materias = new ArrayList<>();
        materias.add("Literatura");
        materias.add("Matematica");
        materias.add("Historia");
        materias.add("Ingles");
        return materias;
    }

}
