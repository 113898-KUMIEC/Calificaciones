package ar.edu.utn.frc.tup.lciv.Client;

import ar.edu.utn.frc.tup.lciv.Model.Alumnos;
import ar.edu.utn.frc.tup.lciv.Model.Docentes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestClient {

    @Autowired
    RestTemplate restTemplate;

    private static final String URL = "http://localhost:8080/";

    public ResponseEntity<List<Alumnos>> getAllAlumnos(){
        return restTemplate.exchange(URL + "/alumnos", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Alumnos>>() {});
    }
    public ResponseEntity<List<Docentes>> getAllDocentes(){
        return restTemplate.exchange(URL + "/docentes", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Docentes>>() {});
    }
}
