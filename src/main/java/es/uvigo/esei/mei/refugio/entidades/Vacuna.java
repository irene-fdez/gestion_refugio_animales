package es.uvigo.esei.mei.refugio.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Irene Fernández
 */
@Entity
public class Vacuna implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private String tipo;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private Date fechaVacunacion;

    @ManyToOne
    private Animal animal;
	
	public Vacuna() {}
	
	public Vacuna(String tipo, Date fechaVacunacion, Animal animal) {
		this.tipo = tipo;
		this.fechaVacunacion = fechaVacunacion;
		this.animal = animal;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
	
	public Date getFechaVacunacion() {
        return fechaVacunacion;
    }

    public void setFechaVacunacion(Date fechaVacunacion) {
        this.fechaVacunacion = fechaVacunacion;
    }
	
	public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    
  
    //@Override
    /*public int hashCode() {
        if (this.id != null) {
            return this.id.hashCode();
        } else {
            int hash = Objects.hashCode(this.nombre);
            return hash;
        }
    }*/
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vacuna other = (Vacuna) obj;
        if (this.id !=null) {
            return this.id.equals(other.id);
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vacuna{" + "id=" + id + ", tipo de vacuna=" + tipo + ", fecha vacuna=" + fechaVacunacion +", animal =" + animal + '}';
    }
}