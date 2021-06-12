package es.uvigo.esei.mei.refugio.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.esei.mei.refugio.daos.TipoDAO;
import es.uvigo.esei.mei.refugio.entidades.Tipo;
import es.uvigo.esei.mei.refugio.entidades.EspeciesAnimales;
import es.uvigo.esei.mei.refugio.servicios.TipoService;
import java.util.Date;

@Service
public class TipoServiceImpl implements TipoService {

	@Autowired
	TipoDAO dao;

	@Override
	@Transactional
	public Tipo crear(Tipo tipo) {
		return dao.save(tipo);
	}

	@Override
	@Transactional
	public Tipo modificar(Tipo tipo) {
		return dao.save(tipo);
	}

	@Override
	@Transactional
	public void eliminar(Tipo tipo) {
		dao.delete(tipo);
	}

	@Override
	@Transactional(readOnly = true)
	public Tipo buscarPorID(Long id) {
		return dao.getById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tipo> buscarTodos() {
		return dao.findAll();
	}

	@Override
	public List<Tipo> buscarNombreRazaEspecie(String nombre, String raza, String especie) {
		return dao.findByNombreRazaEspecie(nombre, raza, especie);
	}
}
