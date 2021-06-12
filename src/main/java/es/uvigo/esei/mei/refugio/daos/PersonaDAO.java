package es.uvigo.esei.mei.refugio.daos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import es.uvigo.esei.mei.refugio.entidades.Persona;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Irene Fernández
 */
public interface PersonaDAO extends JpaRepository<Persona, String> {

    public List<Persona> findAll();

    public Persona getByDNI(String dni);

    @Query(value = "SELECT * FROM Persona  WHERE nombre LIKE CONCAT('%',:nombre,'%') " + "AND especialidad LIKE CONCAT('%',:especialidad,'%') ",
            nativeQuery = true)
    public List<Persona> findByNombreEspecialidad(@Param("nombre") String nombre, @Param("especialidad") String especialidad);

}
