package es.uvigo.esei.mei.refugio.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.esei.mei.refugio.daos.AnimalDAO;
import es.uvigo.esei.mei.refugio.entidades.Animal;
import es.uvigo.esei.mei.refugio.servicios.AnimalService;
import java.util.Date;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    AnimalDAO dao;

    @Override
    @Transactional
    public Animal crear(Animal animal) {
        return dao.save(animal);
    }

    @Override
    @Transactional
    public Animal modificar(Animal animal) {
        return dao.save(animal);
    }

    @Override
    @Transactional
    public void eliminar(Animal animal) {
        dao.delete(animal);
    }

    @Override
    @Transactional(readOnly = true)
    public Animal buscarPorId(Long id) {
        return dao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Animal> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public List<Animal> buscarNombreColor(String nombre, String color) {
        return dao.findByNombreColor(nombre, color);
    }

}
