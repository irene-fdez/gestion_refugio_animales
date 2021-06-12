package es.uvigo.esei.mei.refugio.daos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import es.uvigo.esei.mei.refugio.entidades.Tipo;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Irene Fernández
 */
public interface TipoDAO extends JpaRepository<Tipo, Long> {
	
	public List<Tipo> findAll();

    public Tipo getById(Long id);
	
	@Query(value = "SELECT * FROM Tipo  WHERE nombre LIKE CONCAT('%',:nombre,'%') "
            + "AND raza LIKE CONCAT('%',:raza,'%')" + "AND especie LIKE CONCAT('%',:especie,'%')",
            nativeQuery = true)
    public List<Tipo> findByNombreRazaEspecie(@Param("nombre") String nombre,
            @Param("raza") String raza, @Param("especie") String especie);

}
