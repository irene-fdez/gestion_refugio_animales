package es.uvigo.esei.mei.refugio.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.esei.mei.refugio.daos.VacunaDAO;
import es.uvigo.esei.mei.refugio.entidades.Vacuna;
import es.uvigo.esei.mei.refugio.servicios.VacunaService;
import java.util.Date;

@Service
public class VacunaServiceImpl implements VacunaService {

    @Autowired
    VacunaDAO dao;

    @Override
    @Transactional
    public Vacuna crear(Vacuna vacuna) {
        return dao.save(vacuna);
    }

    @Override
    @Transactional
    public Vacuna modificar(Vacuna vacuna) {
        return dao.save(vacuna);
    }

    @Override
    @Transactional
    public void eliminar(Vacuna vacuna) {
        dao.delete(vacuna);
    }

    @Override
    @Transactional(readOnly = true)
    public Vacuna buscarPorID(Long id) {
        return dao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vacuna> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public List<Vacuna> buscarTipo(String tipo) {
        return dao.findByTipo(tipo);
    }

}
