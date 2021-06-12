package es.uvigo.esei.mei.refugio.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Irene Fernández
 */
@Entity
public class Acogida implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String tipoEntrada;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Temporal(TemporalType.DATE)
	private Date fechaSalida;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Temporal(TemporalType.DATE)
	private Date fechaEntrada;

	@Enumerated(EnumType.STRING)
	private TipoSalida tipoSalida;

	@ManyToOne
	private Centro centro;

	@ManyToOne
	private Animal animal;

	public Acogida() {

	}

	public Acogida(String tipoEntrada, TipoSalida tipoSalida, Date fechaSalida, Date fechaEntrada, Animal animal, Centro centro) {
		this.tipoEntrada = tipoEntrada;
		this.tipoSalida = tipoSalida;
		this.fechaSalida = fechaSalida;
		this.fechaEntrada = fechaEntrada;
		this.animal = animal;
		this.centro = centro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoEntrada() {
		return tipoEntrada;
	}

	public void setTipoEntrada(String tipoEntrada) {
		this.tipoEntrada = tipoEntrada;
	}

	public TipoSalida getTipoSalida() {
		return tipoSalida;
	}

	public void setTipoSalida(TipoSalida tipoSalida) {
		this.tipoSalida = tipoSalida;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	@Override
	public int hashCode() {
		int hash = Objects.hashCode(this.id);
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
		final Acogida other = (Acogida) obj;
		if (this.id != null) {
			return this.id.equals(other.id);
		}
		return true;
	}

	@Override
	public String toString() {
		return "Acogida{" + "id=" + id + ", tipo entrada=" + tipoEntrada + ", tipo salida=" + tipoSalida
				+ ", fecha entrada=" + fechaEntrada + ", fecha salida=" + fechaSalida + ", animal=" + animal
				+ ", centro=" + centro + '}';
	}
}