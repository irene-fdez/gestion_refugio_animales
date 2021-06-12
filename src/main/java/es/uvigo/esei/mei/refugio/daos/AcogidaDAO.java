package es.uvigo.esei.mei.refugio.daos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import es.uvigo.esei.mei.refugio.entidades.Acogida;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Irene Fernández
 */
public interface AcogidaDAO extends JpaRepository<Acogida, Long> {

    public List<Acogida> findAll();

    public Acogida getById(Long id);

    @Query(value = "SELECT * FROM Acogida WHERE tipoEntrada LIKE CONCAT('%',:tipoEntrada,'%') " + "AND tipoSalida LIKE CONCAT('%',:tipoSalida,'%') ",
            nativeQuery = true)
    public List<Acogida> findByTipoEntradaTipoSalida(@Param("tipoEntrada") String tipoEntrada, @Param("tipoSalida") String tipoSalida);
    
    @Query(value = "SELECT * FROM Acogida WHERE tipoEntrada LIKE CONCAT('%',:tipoEntrada,'%')",
            nativeQuery = true)
    public List<Acogida> findByTipoEntrada(@Param("tipoEntrada") String tipoEntrada);

}
