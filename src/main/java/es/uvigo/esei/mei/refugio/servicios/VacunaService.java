package es.uvigo.esei.mei.refugio.servicios;

import java.util.List;

import es.uvigo.esei.mei.refugio.entidades.Vacuna;
import org.springframework.stereotype.Service;

@Service
public interface VacunaService {

    public Vacuna crear(Vacuna vacuna);

    public Vacuna modificar(Vacuna vacuna);

    public void eliminar(Vacuna vacuna);

    public Vacuna buscarPorID(Long id);

    public List<Vacuna> buscarTodos();
    
    public List<Vacuna> buscarTipo(String tipo);


}