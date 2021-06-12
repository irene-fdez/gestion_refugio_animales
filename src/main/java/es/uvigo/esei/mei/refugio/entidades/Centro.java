package es.uvigo.esei.mei.refugio.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Irene Fernández
 */
@Entity
public class Centro implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String direccion;
	
	@ManyToOne
	private Ciudad ciudad;
	
	public Centro() {}
	
	public Centro(String nombre, String direccion, Ciudad ciudad) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.ciudad = ciudad;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
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
        final Centro other = (Centro) obj;
        if (this.id !=null) {
            return this.id.equals(other.id);
        }
        /*if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }*/
        return true;
    }

    @Override
    public String toString() {
        return "Centro{" + "id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", ciudad=" + ciudad +'}';
    }
}
