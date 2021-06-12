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
public class Animal implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	private String color;

	@ManyToOne
	private Persona adoptante;

	@ManyToOne
	private Tipo tipo;

	public Animal() {
	}

	public Animal(String nombre, Date fechaNacimiento, String color, Tipo tipo, Persona adoptante) {
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.color = color;
		this.adoptante = adoptante;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Persona getAdoptante() {
		return adoptante;
	}

	public void setAdoptante(Persona adoptante) {
		this.adoptante = adoptante;
	}

	// @Override
	/*
	 * public int hashCode() { if (this.id != null) { return this.id.hashCode(); }
	 * else { int hash = Objects.hashCode(this.nombre); return hash; } }
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
		final Animal other = (Animal) obj;
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
		return "Animal{" + "id=" + id + ", nombre=" + nombre + ", fecha nacimiento =" + fechaNacimiento + ", color=" + color
				+ ", tipo=" + tipo + ", adoptante=" + adoptante + '}';
	}
}