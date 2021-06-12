package es.uvigo.esei.mei.refugio.servicios;
import java.util.List;

import es.uvigo.esei.mei.refugio.entidades.Tipo;
import es.uvigo.esei.mei.refugio.entidades.EspeciesAnimales;
import org.springframework.stereotype.Service;

public interface TipoService {
	
	public Tipo crear(Tipo tipo);

    public Tipo modificar(Tipo tipo);

    public void eliminar(Tipo tipo);

    public Tipo buscarPorID(Long id);

    public List<Tipo> buscarTodos();
    
    public List<Tipo> buscarNombreRazaEspecie(String nombre, String raza, String especie);

}
