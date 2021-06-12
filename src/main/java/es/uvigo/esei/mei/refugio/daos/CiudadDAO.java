package es.uvigo.esei.mei.refugio.daos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import es.uvigo.esei.mei.refugio.entidades.Ciudad;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Irene Fernández
 */
public interface CiudadDAO extends JpaRepository<Ciudad, Long> {

    public List<Ciudad> findAll();

    public Ciudad getById(Long id);

    @Query(value = "SELECT * FROM Ciudad  WHERE nombre LIKE CONCAT('%',:nombre,'%') ",
            nativeQuery = true)
    public List<Ciudad> findByNombre(@Param("nombre") String nombre);

}
