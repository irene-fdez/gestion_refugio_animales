package es.uvigo.esei.mei.refugio.servicios;

import java.util.List;

import es.uvigo.esei.mei.refugio.entidades.Ciudad;
import org.springframework.stereotype.Service;

@Service
public interface CiudadService {

    public Ciudad crear(Ciudad ciudad);

    public Ciudad modificar(Ciudad ciudad);

    public void eliminar(Ciudad ciudad);

    public Ciudad buscarPorID(Long id);

    public List<Ciudad> buscarTodos();
    
    public List<Ciudad> buscarNombre(String nombre);


}
