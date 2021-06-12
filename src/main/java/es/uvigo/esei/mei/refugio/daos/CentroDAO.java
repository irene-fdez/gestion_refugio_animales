package es.uvigo.esei.mei.refugio.daos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import es.uvigo.esei.mei.refugio.entidades.Centro;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Irene Fernández
 */
public interface CentroDAO extends JpaRepository<Centro, Long> {

    public List<Centro> findAll();

    public Centro getById(Long id);

    @Query(value = "SELECT * FROM Centro  WHERE nombre LIKE CONCAT('%',:nombre,'%') " + "AND ciudad LIKE CONCAT('%',:ciudad,'%')",
            nativeQuery = true)
    public List<Centro> findByNombreCiudad(@Param("nombre") String nombre, @Param("ciudad") String ciudad);

}
