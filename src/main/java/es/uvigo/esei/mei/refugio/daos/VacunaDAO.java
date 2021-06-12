package es.uvigo.esei.mei.refugio.daos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import es.uvigo.esei.mei.refugio.entidades.Vacuna;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Irene Fernández
 */
public interface VacunaDAO extends JpaRepository<Vacuna, Long> {

    public List<Vacuna> findAll();

    public Vacuna getById(Long id);

    @Query(value = "SELECT * FROM Vacuna WHERE tipo LIKE CONCAT('%',:tipo,'%')",
            nativeQuery = true)
    public List<Vacuna> findByTipo(@Param("tipo") String tipo);

}
