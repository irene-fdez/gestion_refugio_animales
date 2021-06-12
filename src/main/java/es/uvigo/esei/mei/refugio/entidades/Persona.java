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
public class Persona implements Serializable {
	@Id
	private String DNI;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	private String nombre;

	private String especialidad;

	@ManyToOne
	private Centro centroTrabajo;

	public Persona() {
	}

	public Persona(String DNI, Date fechaNacimiento, String nombre, String especialidad, Centro centroTrabajo) {
		this.DNI = DNI;
		this.fechaNacimiento = fechaNacimiento;
		this.nombre = nombre;
		this.especialidad = especialidad;
		this.centroTrabajo = centroTrabajo;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String DNI) {
		this.DNI = DNI;
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

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public Centro getCentroTrabajo() {
		return centroTrabajo;
	}

	public void setCentroTrabajo(Centro centroTrabajo) {
		this.centroTrabajo = centroTrabajo;
	}

	@Override
	public int hashCode() {
		int hash = Objects.hashCode(this.DNI);
		return hash;
	}

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
		final Persona other = (Persona) obj;
		if (!Objects.equals(this.DNI, other.DNI)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Persona{" + "DNI=" + DNI + ", nombre=" + nombre + ", fecha nacimiento =" + fechaNacimiento
				+ ", especialidad=" + especialidad + ", centro de trabajo=" + centroTrabajo + '}';
	}
}