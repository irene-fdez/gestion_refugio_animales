package es.uvigo.esei.mei.refugio.daos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import es.uvigo.esei.mei.refugio.entidades.Animal;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Irene Fernández
 */
public interface AnimalDAO extends JpaRepository<Animal, Long> {

    public List<Animal> findAll();

    public Animal getById(Long id);

    @Query(value = "SELECT * FROM Animal  WHERE nombre LIKE CONCAT('%',:nombre,'%') " + "AND color LIKE CONCAT('%',:color,'%') ",
            nativeQuery = true)
    public List<Animal> findByNombreColor(@Param("nombre") String nombre, @Param("color") String color);

}
