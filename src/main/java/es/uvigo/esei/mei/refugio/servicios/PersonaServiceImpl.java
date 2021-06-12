package es.uvigo.esei.mei.refugio.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.esei.mei.refugio.daos.PersonaDAO;
import es.uvigo.esei.mei.refugio.entidades.Persona;
import es.uvigo.esei.mei.refugio.servicios.PersonaService;
import java.util.Date;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaDAO dao;

    @Override
    @Transactional
    public Persona crear(Persona persona) {
        return dao.save(persona);
    }

    @Override
    @Transactional
    public Persona modificar(Persona persona) {
        return dao.save(persona);
    }

    @Override
    @Transactional
    public void eliminar(Persona persona) {
        dao.delete(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona buscarPorDNI(String dni) {
        return dao.getByDNI(dni);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public List<Persona> buscarNombreEspecialidad(String nombre, String especialidad) {
        return dao.findByNombreEspecialidad(nombre, especialidad);
    }

}
