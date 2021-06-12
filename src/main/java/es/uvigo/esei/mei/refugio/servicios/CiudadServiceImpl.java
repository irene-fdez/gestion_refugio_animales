package es.uvigo.esei.mei.refugio.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.esei.mei.refugio.daos.CiudadDAO;
import es.uvigo.esei.mei.refugio.entidades.Ciudad;
import es.uvigo.esei.mei.refugio.servicios.CiudadService;
import java.util.Date;

@Service
public class CiudadServiceImpl implements CiudadService {

    @Autowired
    CiudadDAO dao;

    @Override
    @Transactional
    public Ciudad crear(Ciudad ciudad) {
        return dao.save(ciudad);
    }

    @Override
    @Transactional
    public Ciudad modificar(Ciudad ciudad) {
        return dao.save(ciudad);
    }

    @Override
    @Transactional
    public void eliminar(Ciudad ciudad) {
        dao.delete(ciudad);
    }

    @Override
    @Transactional(readOnly = true)
    public Ciudad buscarPorID(Long id) {
        return dao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ciudad> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public List<Ciudad> buscarNombre(String nombre) {
        return dao.findByNombre(nombre);
    }

}
