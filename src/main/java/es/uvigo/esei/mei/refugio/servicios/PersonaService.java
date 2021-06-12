package es.uvigo.esei.mei.refugio.servicios;

import java.util.List;

import es.uvigo.esei.mei.refugio.entidades.Persona;
import org.springframework.stereotype.Service;

@Service
public interface PersonaService {

    public Persona crear(Persona persona);

    public Persona modificar(Persona persona);

    public void eliminar(Persona persona);

    public Persona buscarPorDNI(String dni);

    public List<Persona> buscarTodos();
    
    public List<Persona> buscarNombreEspecialidad(String persona, String especialidad);


}
