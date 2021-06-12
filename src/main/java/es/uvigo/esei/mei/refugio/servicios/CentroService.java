package es.uvigo.esei.mei.refugio.servicios;

import java.util.List;

import es.uvigo.esei.mei.refugio.entidades.Centro;
import org.springframework.stereotype.Service;

@Service
public interface CentroService {

    public Centro crear(Centro centro);

    public Centro modificar(Centro centro);

    public void eliminar(Centro centro);

    public Centro buscarPorID(Long id);

    public List<Centro> buscarTodos();
    
    public List<Centro> buscarNombreCiudad(String nombre, String ciudad);


}
