package es.uvigo.esei.mei.refugio.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.esei.mei.refugio.daos.CentroDAO;
import es.uvigo.esei.mei.refugio.entidades.Centro;
import es.uvigo.esei.mei.refugio.servicios.CentroService;
import java.util.Date;

@Service
public class CentroServiceImpl implements CentroService {

    @Autowired
    CentroDAO dao;

    @Override
    @Transactional
    public Centro crear(Centro centro) {
        return dao.save(centro);
    }

    @Override
    @Transactional
    public Centro modificar(Centro centro) {
        return dao.save(centro);
    }

    @Override
    @Transactional
    public void eliminar(Centro centro) {
        dao.delete(centro);
    }

    @Override
    @Transactional(readOnly = true)
    public Centro buscarPorID(Long id) {
        return dao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Centro> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public List<Centro> buscarNombreCiudad(String nombre, String ciudad) {
        return dao.findByNombreCiudad(nombre, ciudad);
    }

}
