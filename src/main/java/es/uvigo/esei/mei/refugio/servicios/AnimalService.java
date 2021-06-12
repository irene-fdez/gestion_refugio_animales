package es.uvigo.esei.mei.refugio.servicios;

import java.util.List;

import es.uvigo.esei.mei.refugio.entidades.Animal;
import org.springframework.stereotype.Service;

@Service
public interface AnimalService {

    public Animal crear(Animal animal);

    public Animal modificar(Animal animal);

    public void eliminar(Animal animal);

    public Animal buscarPorId(Long id);

    public List<Animal> buscarTodos();
    
    public List<Animal> buscarNombreColor(String nombre, String color);


}
