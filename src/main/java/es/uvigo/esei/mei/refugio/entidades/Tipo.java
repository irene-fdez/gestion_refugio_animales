package es.uvigo.esei.mei.refugio.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Irene Fernández
 */
@Entity
public class Tipo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	private String raza;
	
	@Enumerated(EnumType.STRING)
	private EspeciesAnimales especie;

	public Tipo() {
	}

	public Tipo(String nombre, String raza, EspeciesAnimales especie) {
		this.nombre = nombre;
		this.raza = raza;
		this.especie = especie;
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

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public EspeciesAnimales getEspecie() {
		return especie;
	}

	public void setEspecie(EspeciesAnimales especie) {
		this.especie = especie;
	}

	/*
	 * @Override public int hashCode() { if (this.id != null) { return
	 * this.id.hashCode(); } else { int hash = Objects.hashCode(this.nombre); return
	 * hash; } }
	 */

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
		final Tipo other = (Tipo) obj;
		if (this.id != null) {
			return this.id.equals(other.id);
		}
		if (!Objects.equals(this.nombre, other.nombre)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Tipo{" + "id=" + id + ", nombre=" + nombre + ", raza=" + raza + ", especie=" + especie + '}';
	}
}